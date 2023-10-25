CREATE TABLE managers
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name  VARCHAR(50),
    status     VARCHAR(16),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE products
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    name          VARCHAR(60),
    status        VARCHAR(16),
    currency_code VARCHAR(8),
    interest_rate NUMERIC(6, 4),
    limit_amount  INTEGER,
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP,
    manager_id    BIGINT,
    FOREIGN KEY (manager_id) REFERENCES managers (id)
);

CREATE TABLE clients
(
    id                BIGINT PRIMARY KEY AUTO_INCREMENT,
    status            VARCHAR(20),
    tax_code          VARCHAR(20) UNIQUE,
    first_name        VARCHAR(40),
    last_name         VARCHAR(40),
    email             VARCHAR(60) UNIQUE,
    address           VARCHAR(90),
    phone             VARCHAR(25),
    created_at        TIMESTAMP,
    updated_at        TIMESTAMP,
    manager_client_id BIGINT,
    FOREIGN KEY (manager_client_id) REFERENCES managers (id)
);

CREATE TABLE accounts
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    name          VARCHAR(90),
    type          VARCHAR(16),
    status        VARCHAR(16),
    balance       DECIMAL(12, 2),
    currency_code VARCHAR(8),
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP,
    client_id     BIGINT,
    FOREIGN KEY (client_id) REFERENCES clients (id)
);

CREATE TABLE agreements
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    status     VARCHAR(12),
    total      DECIMAL(14, 2),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    account_id BIGINT UNIQUE,
    product_id BIGINT,
    FOREIGN KEY (account_id) REFERENCES accounts (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);

CREATE TABLE transactions
(
    id                BIGINT PRIMARY KEY AUTO_INCREMENT,
    type              VARCHAR(12),
    amount            DECIMAL(12, 4),
    description       VARCHAR(255),
    created_at        TIMESTAMP,
    debit_account_id  BIGINT,
    credit_account_id BIGINT,
    FOREIGN KEY (debit_account_id) REFERENCES accounts (id),
    FOREIGN KEY (credit_account_id) REFERENCES accounts (id)
);
