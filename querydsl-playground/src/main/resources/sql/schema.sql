CREATE TABLE POST
(
    post_id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    title      VARCHAR(255) NOT NULL,
    writer     VARCHAR(255) NOT NULL,
    content    VARCHAR(255) NOT NULL,
    created_at DATETIME     NOT NULL,
    updated_at DATETIME     NOT NULL
);

CREATE TABLE COMMENT
(
    comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id    BIGINT,
    writer     VARCHAR(255) NOT NULL,
    content    VARCHAR(255) NOT NULL,
    delete     VARCHAR(255) CHECK (delete IN ('Y', 'N')),
    created_at DATETIME     NOT NULL,
    updated_at DATETIME     NOT NULL
);

ALTER TABLE COMMENT
    ADD CONSTRAINT FK_COMMENT_POST FOREIGN KEY (post_id) REFERENCES POST;