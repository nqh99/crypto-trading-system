CREATE TABLE "order"
(
    id         UUID PRIMARY KEY NOT NULL,
    user_id    VARCHAR(255)     NOT NULL,
    crypto_id  VARCHAR(255)     NOT NULL,
    order_type VARCHAR(30)      NOT NULL,
    price      DECIMAL(22, 7) DEFAULT 0,
    qty        DECIMAL(22, 7) DEFAULT 0,
    filled_qty DECIMAL(22, 7) DEFAULT 0,
    bs         CHAR(1)          NOT NULL,
    status     VARCHAR(30)      NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,

    FOREIGN KEY (user_id) REFERENCES "user" (id) ON DELETE CASCADE,
    FOREIGN KEY (crypto_id) REFERENCES crypto (id) ON DELETE CASCADE
);

CREATE INDEX idx_order_1 ON "order" (user_id, status);
CREATE INDEX idx_order_2 ON "order" (crypto_id, status);
CREATE INDEX idx_order_3 ON "order" (bs, status);
CREATE INDEX idx_order_4 ON "order" (status);