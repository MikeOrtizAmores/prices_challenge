CREATE SEQUENCE IF NOT EXISTS PRICES_SEQ START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS PRICES
(
    PRICE_LIST BIGINT DEFAULT NEXT VALUE FOR PRICES_SEQ PRIMARY KEY,
    PRODUCT_ID BIGINT         NOT NULL,
    BRAND_ID   INTEGER        NOT NULL,
    START_DATE TIMESTAMP      NOT NULL,
    END_DATE   TIMESTAMP      NOT NULL,
    PRICE      NUMERIC(10, 2) NOT NULL,
    PRIORITY   INTEGER,
    CURR       VARCHAR(3)     NOT NULL
);
