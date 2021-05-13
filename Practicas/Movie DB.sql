CREATE TABLE `Movie`(
    `Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `title` TEXT NOT NULL,
    `release_date` DATE NOT NULL,
    `category_id` INT NOT NULL
);
ALTER TABLE
    `Movie` ADD PRIMARY KEY `movie_id_primary`(`Id`);
ALTER TABLE
    `Movie` ADD UNIQUE `movie_category_id_unique`(`category_id`);
CREATE TABLE `Category`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` TEXT NOT NULL
);
ALTER TABLE
    `Category` ADD PRIMARY KEY `category_id_primary`(`id`);
CREATE TABLE `Rating`(
    `rating_value` INT NOT NULL,
    `review` TEXT NOT NULL,
    `user_id` INT UNSIGNED NOT NULL,
    `movie_id` INT NOT NULL
);
ALTER TABLE
    `Rating` ADD UNIQUE `rating_user_id_unique`(`user_id`);
ALTER TABLE
    `Rating` ADD UNIQUE `rating_movie_id_unique`(`movie_id`);
CREATE TABLE `User`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `first_name` TEXT NOT NULL,
    `last_name` TEXT NOT NULL,
    `username` TEXT NOT NULL
);
ALTER TABLE
    `User` ADD PRIMARY KEY `user_id_primary`(`id`);
ALTER TABLE
    `Rating` ADD CONSTRAINT `rating_movie_id_foreign` FOREIGN KEY(`movie_id`) REFERENCES `Movie`(`Id`);
ALTER TABLE
    `Movie` ADD CONSTRAINT `movie_category_id_foreign` FOREIGN KEY(`category_id`) REFERENCES `Category`(`id`);
ALTER TABLE
    `Rating` ADD CONSTRAINT `rating_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `User`(`id`);