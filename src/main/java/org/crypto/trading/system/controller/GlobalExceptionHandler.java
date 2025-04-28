package org.crypto.trading.system.controller;

import jakarta.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.crypto.trading.system.dto.common.ExceptionDto;
import org.crypto.trading.system.exception.BusinessException;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  public ResponseEntity<Object> handleHandlerMethodValidationException(
      HandlerMethodValidationException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    List<Map<String, Object>> detailList =
        ex.getParameterValidationResults().stream()
            .map(
                validationResult -> {
                  Map<String, Object> detail = new HashMap<>();
                  detail.put("field", validationResult.getMethodParameter().getParameterName());
                  detail.put("index", validationResult.getMethodParameter().getParameterIndex());

                  List<String> violations =
                      validationResult.getResolvableErrors().stream()
                          .map(MessageSourceResolvable::getDefaultMessage)
                          .collect(Collectors.toList());

                  detail.put("violations", violations);
                  return detail;
                })
            .toList();

    ExceptionDto responseDto =
        new ExceptionDto(
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            ((ServletWebRequest) request).getRequest().getServletPath(),
            "Validation error",
            detailList);

    logError(
        MessageFormat.format("{0} [{1}]", responseDto.getMessage(), responseDto.getDetail()),
        ((ServletWebRequest) request).getRequest());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ExceptionDto> handleBusinessException(
      BusinessException exception, HttpServletRequest request) {
    logError(
        MessageFormat.format("{0} [{1}]", exception.getMessage(), exception.getDetail()), request);

    return ResponseEntity.status(exception.getStatus())
        .body(
            new ExceptionDto(
                exception.getStatus().getReasonPhrase(),
                request.getServletPath(),
                exception.getMessage(),
                exception.getDetail()));
  }

  private void logError(String msg, HttpServletRequest request) {
    log.error(
        "Error: {} | URI: {} | Method: {} | Client IP: {} | User-Agent: {}",
        msg,
        request.getServletPath(),
        request.getMethod(),
        request.getRemoteAddr(),
        request.getHeader("User-Agent"));
  }
}
