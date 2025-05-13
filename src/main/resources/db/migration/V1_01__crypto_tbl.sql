CREATE TABLE crypto
(
    id         uuid PRIMARY KEY NOT NULL,
    symbol     VARCHAR(255)     NOT NULL,
    open       DECIMAL(22, 7) DEFAULT 0,
    high       DECIMAL(22, 7) DEFAULT 0,
    low        DECIMAL(22, 7) DEFAULT 0,
    close      DECIMAL(22, 7) DEFAULT 0,
    bid        DECIMAL(22, 7) DEFAULT 0,
    bid_qty    DECIMAL(22, 7) DEFAULT 0,
    ask        DECIMAL(22, 7) DEFAULT 0,
    ask_qty    DECIMAL(22, 7) DEFAULT 0,
    status     VARCHAR(30)      NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE INDEX idx_crypto_1 ON crypto (status);
CREATE INDEX idx_crypto_2 ON crypto (symbol, status);