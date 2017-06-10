CREATE TABLE `product` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`category` VARCHAR(80) NOT NULL,
	`description` VARCHAR(255) NOT NULL,
	`name` VARCHAR(60) NOT NULL,
	`price` DECIMAL(12,2) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE INDEX index_categories ON product(category);