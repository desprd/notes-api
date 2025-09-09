CREATE TABLE IF NOT EXISTS author(
    author_id BIGINT AUTO_INCREMENT  PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS note(
    note_id BIGINT AUTO_INCREMENT  PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    content CLOB,
    created_at TIMESTAMP NOT NULL,
    author_id BIGINT NOT NULL,
    CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES author(author_id)
);