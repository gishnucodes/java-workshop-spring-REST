CREATE TABLE IF NOT EXISTS posts (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       author VARCHAR(255) NOT NULL,
                       title VARCHAR(255) NOT NULL,
                       content VARCHAR(1000) NOT NULL,
                       date TIMESTAMP NOT NULL
);