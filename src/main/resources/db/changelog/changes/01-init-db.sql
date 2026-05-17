--liquibase formatted sql

--changeset ainur-davletchurin:1-init-database
CREATE TABLE users (
  id BIGSERIAL PRIMARY KEY,
  user_name VARCHAR(255) NOT NULL
);

CREATE TABLE news_categories (
  id BIGSERIAL PRIMARY KEY,
  category_name VARCHAR(255) NOT NULL
);

CREATE TABLE news (
  id BIGSERIAL PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  description TEXT,
  category_id BIGINT,
  user_id BIGINT,
  create_at TIMESTAMP WITHOUT TIME ZONE,
  update_at TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT fk_news_category FOREIGN KEY (category_id) REFERENCES news_categories(id) ON DELETE SET NULL,
  CONSTRAINT fk_news_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE comments (
  id BIGSERIAL PRIMARY KEY,
  description TEXT NOT NULL,
  user_id BIGINT,
  news_id BIGINT,
  create_at TIMESTAMP WITHOUT TIME ZONE,
  update_at TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT fk_comments_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
  CONSTRAINT fk_comments_news FOREIGN KEY (news_id) REFERENCES news(id) ON DELETE CASCADE
);