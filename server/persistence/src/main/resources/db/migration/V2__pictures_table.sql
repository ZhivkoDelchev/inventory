CREATE TABLE `picture` (
	`id` INT(11) NOT NULL AUTO_INCREMENT,
	`product` INT(11) NOT NULL,
	data MEDIUMBLOB NOT NULL,
	PRIMARY KEY (`id`),
	FOREIGN KEY (product) REFERENCES product(id)
) ENGINE=InnoDB;