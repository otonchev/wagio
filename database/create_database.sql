CREATE DATABASE IF NOT EXISTS `organization`;
USE `organization`;

CREATE TABLE IF NOT EXISTS `members` (
  `member_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `parent_id` int(11),
  `enabled` bool NOT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `member_roles` (
  `member_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`member_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `members` (`member_id`, `first_name`, `last_name`, `email`, `password`, `parent_id`, `enabled`) VALUES
	(1, 'John', 'Bell', 'john@gmail.com', '$2a$04$5nsfkmBeVSlYemNgYCLrJuv7es5szUiSOaE6f3u7PkIrUqOPU74rS', 0, true),
	(2, 'Bob', 'Funk', 'bob@gmail.com', '$2a$04$5nsfkmBeVSlYemNgYCLrJuv7es5szUiSOaE6f3u7PkIrUqOPU74rS', 1, true),
	(3, 'Anton', 'Smith', 'anton@gmail.com', '$2a$04$5nsfkmBeVSlYemNgYCLrJuv7es5szUiSOaE6f3u7PkIrUqOPU74rS', 1, true),
	(4, 'Daniel', 'Wilson', 'daniel@gmail.com', '$2a$04$5nsfkmBeVSlYemNgYCLrJuv7es5szUiSOaE6f3u7PkIrUqOPU74rS', 2, true),
	(5, 'Alex', 'Alex', 'daniel@gmail.com', '$2a$04$5nsfkmBeVSlYemNgYCLrJuv7es5szUiSOaE6f3u7PkIrUqOPU74rS', 3, true);

INSERT INTO `roles` (`role_id`, `role`) VALUES
	(1, "ROLE_ADMIN"),
        (2, "ROLE_USER"),
	(3, "ROLE_MANAGER");

INSERT INTO `member_roles` (`member_id`, `role_id`) VALUES
        (1, 1),
        (1, 2),
        (1, 3),
        (2, 2),
        (2, 3),
        (3, 2),
        (3, 3),
        (4, 2),
        (5, 2);
