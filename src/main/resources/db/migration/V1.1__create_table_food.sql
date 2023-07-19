CREATE TABLE IF NOT EXISTS food (
    id              bigserial PRIMARY KEY,
    description     varchar(200),
    code            INTEGER NOT NULL,
    title           varchar(100) NOT NULL
);