INSERT INTO managers (first_name, last_name, status, created_at, updated_at)
VALUES ('John', 'Smith', 'ACTIVE', '2020-08-01', '2022-11-10'),
       ('Mickael', 'Jonson', 'ACTIVE', '2017-05-04', '2020-08-06');

INSERT INTO products (manager_id, name, status, currency_code, interest_rate, limit_amount, created_at, updated_at)
VALUES (1, 'deposit', 'ACTIVE', 'EUR', 10.4, 3000000, '2021-09-14', '2021-11-03'),
       (1, 'credit', 'ACTIVE', 'EUR', 9.5, 4000000, '2017-09-17', '2020-11-06');

INSERT INTO clients (status, tax_code, first_name, last_name, email, address, phone, created_at, updated_at,
                     manager_client_id)
VALUES ('ACTIVE', '222606111', 'Amanda', 'Spears', 'Amspr@gmail.com', 'Germany,Essen, str.Brunenstrasse, 1',
        '+49-175-660-21-13', '2021-01-01', '2021-01-02', 1),
       ('ACTIVE', '222606122', 'Shon', 'Robinson', 'Robinshon@gmail.com', 'Germany,Dusseldorf, str.Kreuzerstrasse, 12',
        '+49-175-967-61-15', '2022-06-06', '2022-06-09', 2),
       ('BLOCKED', '222606123', 'Ron', 'Davidson', 'Davidson@gmail.com', 'Germany,Dusseldorf, str.Kreuzerstrasse, 15',
        '+49-175-967-14-15', '2022-06-06', '2022-06-09', 2);

INSERT INTO accounts (name, type, status, currency_code, balance, created_at, updated_at, client_id)
VALUES ('4444566543212345', 'DEPOSIT', 'ACTIVE', 'EUR', 20000, '2022-04-01', '2022-04-10', 1),
       ('4444566543212356', 'CREDIT', 'ACTIVE', 'EUR', 50000, '2021-06-02', '2021-07-01', 2);

INSERT INTO agreements (status, total, created_at, updated_at, account_id, product_id)
VALUES ('ACTIVE', 15000, '2023-04-03', '2023-04-09', 1, 1);

INSERT INTO transactions (type, amount, description, created_at, debit_account_id, credit_account_id)
VALUES ('APPROVED', 12000, 'Payment by agreement 01 from 2023-04-02', '2023-04-03', 1, 1);





