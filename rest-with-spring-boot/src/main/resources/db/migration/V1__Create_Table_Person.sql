CREATE TABLE IF NOT EXISTS `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(50) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
)