CREATE
DATABASE `statistics` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_polish_ci */;

CREATE TABLE `statistics`.`restaurantsincome`
(
    `id`              VARCHAR(255) NOT NULL AUTO_INCREMENT,
    `voivodeshipName` VARCHAR(255) NOT NULL,
    `year`            INT(6) NOT NULL,
    `value`           FLOAT(15)    NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;


