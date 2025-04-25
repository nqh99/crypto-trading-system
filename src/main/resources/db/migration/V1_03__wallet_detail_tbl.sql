CREATE TABLE wallet_detail
(
    id         uuid PRIMARY KEY NOT NULL,
    wallet_id  uuid             NOT NULL,
    crypto_id  uuid             NOT NULL,
    amount     DECIMAL(22, 7) DEFAULT 0,
    status     VARCHAR(30)      NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,

    FOREIGN KEY (wallet_id) REFERENCES wallet (id) ON DELETE CASCADE,
    FOREIGN KEY (crypto_id) REFERENCES crypto (id) ON DELETE CASCADE
);

CREATE INDEX idx_wallet_detail_1 ON wallet_detail (wallet_id, status);
CREATE INDEX idx_wallet_detail_2 ON wallet_detail (crypto_id, status);