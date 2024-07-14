-- Users table
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) UNIQUE NOT NULL,
  `department_id` INT NOT NULL,
   `role` varchar(255) NOT NULL,
  `state` VARCHAR(255) NOT NULL,
  `branch` VARCHAR(255) NOT NULL,
  `password_hash` VARCHAR(255) NOT NULL,
  `email_verified` BOOLEAN NOT NULL DEFAULT FALSE,
  `interaction_count` INT NOT NULL DEFAULT 0,
  `idea_count` INT NOT NULL DEFAULT 0
);