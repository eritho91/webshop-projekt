CREATE TABLE product
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    price       DOUBLE(10, 2) NOT NULL,
    stock       INT            NOT NULL,
    description VARCHAR(1000),
    image_url   VARCHAR(500),
    category_id BIGINT,
    FOREIGN KEY (category_id) REFERENCES category (id)
);