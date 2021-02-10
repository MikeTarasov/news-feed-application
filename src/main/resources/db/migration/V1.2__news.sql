CREATE TABLE news(
    id          bigserial PRIMARY KEY NOT NULL,
    name        varchar(255)          NOT NULL,
    text        text                  NOT NULL,
    date        timestamp             NOT NULL,
    category_id bigint REFERENCES category (id)
);