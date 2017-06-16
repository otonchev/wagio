CREATE DATABASE IF NOT EXISTS `organization`;
USE `organization`;

CREATE TABLE IF NOT EXISTS `members` (
  `member_id` int(5) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `parent` int(5),
  `enabled` bool,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

INSERT INTO `members` (`member_id`, `first_name`, `last_name`, `email`, `password`, `parent`, `enabled`) VALUES
	(1, 'John', 'Bell', 'john@gmail.com', 'xxx', NULL, 'true'),
	(2, 'Bob', 'Funk', 'bob@gmail.com', 'xxx', 1, 'true'),
	(3, 'Anton', 'Smith', 'anton@gmail.com', 'xxx', 1, 'true'); 
