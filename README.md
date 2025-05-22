# Crypto Trading System

A lightweight, self-hosted backend service that lets you monitor real-time market data and place simulated spot orders
like the most popular crypto exchanges.  
The project is built entirely on the Spring ecosystem such as:
<div style=display:flex;flex-direction:column;gap:3px>
   <div style=display:flex;justify-content:center;align-items:center;gap:3px><a href=https://vscode.dev/github/your-username/your-repo><img alt="Spring Data JPA"src="https://img.shields.io/badge/Spring%20Web-6dbf4b?logo=spring&logoColor=white"></a><a href=https://vscode.dev/github/your-username/your-repo><img alt="Spring Data JPA"src="https://img.shields.io/badge/Spring%20Validation-6dbf4b?logo=spring&logoColor=white"></a><a href=https://vscode.dev/github/your-username/your-repo><img alt="Spring Data JPA"src="https://img.shields.io/badge/Spring%20Data%20JPA-6dbf4b?logo=spring&logoColor=white"></a></div>
   <div style=display:flex;justify-content:center;align-items:center;gap:3px><a href=https://vscode.dev/github/your-username/your-repo><img alt=Flyway src="https://img.shields.io/badge/Flyway-CC0200?logo=flyway&logoColor=white"></a><a href=https://vscode.dev/github/your-username/your-repo><img alt=Flyway src="https://img.shields.io/badge/H2-094568?logo=h2database&logoColor=white"></a><a href=https://vscode.dev/github/your-username/your-repo><img alt=Mapstruct src="https://img.shields.io/badge/MapStruct-orange.svg?logo=data:image/svg%2bxml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAxMDAgMTAwIj4KICA8cGF0aCBkPSJNMjAgNzVMNDAgMjVMNTAgNjBMNjAgMjVMODAgNzUiIHN0cm9rZT0id2hpdGUiIHN0cm9rZS13aWR0aD0iOCIgZmlsbD0ibm9uZSIvPgogIDxwYXRoIGQ9Ik0zMCA1MGg0MCIgc3Ryb2tlPSJ3aGl0ZSIgc3Ryb2tlLXdpZHRoPSI0Ii8+CiAgPHBhdGggZD0iTTMwIDQwaDQwIiBzdHJva2U9IndoaXRlIiBzdHJva2Utd2lkdGg9IjQiLz4KPC9zdmc+"></a><a href=https://vscode.dev/github/your-username/your-repo><img alt=Lombok src="https://img.shields.io/badge/Lombok-CC0200.svg?logo=data:image/svg%2bxml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAxMDAgMTAwIj4KICA8cGF0aCBkPSJNMzAgODBDNjAgODAgODAgNjAgODAgMzBDODAgMzAgNzAgNDAgNjAgNDBDNTAgNDAgNDAgMzAgNDAgMjBDNDAgMTAgNTAgNSA1MCA1QzMwIDUgNSAzMCA1IDYwQzUgNzAgMTAgODAgMzAgODBaIiBmaWxsPSJ3aGl0ZSIvPgogIDxwYXRoIGQ9Ik00MCA2MEM0NSA1NSA0NSA0NSA0MCA0MCIgc3Ryb2tlPSJ3aGl0ZSIgc3Ryb2tlLXdpZHRoPSIzIiBmaWxsPSJub25lIi8+Cjwvc3ZnPg=="></a></div>
</div>

## Key Features

#### Price Aggregation

— Polls Binance and Huobi every 10 seconds.  
— Stores the latest `OHLCV` snapshot in H2.

#### Trading Engine

— Market & limit order creation.  
— Simple matching engine executes the order against the most recent price feed.  
— Order state: NEW → FILLED / PARTIALLY_FILLED / PENDING

#### Trade History

— Retrieve all orders for the user (assuming they are authenticated).  

#### Account & Balances

— Reflects realized/unrealised `PnL` after every trade.

## Getting Started

1. Clone: `git clone https://github.com/nqh99/crypto-trading-system.git`
2. Environment variables (the default is okay if customization isn't necessary).
3. Start infrastructure: `docker compose up --build`

---

**Happy trading!**  
_Please refrain from using it for enterprise; it's merely a demo project. Thank you for your understanding._  
_And give me a `⭐` if you enjoy it!_