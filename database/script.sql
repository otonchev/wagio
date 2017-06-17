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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `member_roles` (
  `member_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`member_role_id`),
  UNIQUE KEY uni_member_role (`role`,`member_id`),
  KEY fk_member_id_idx (`member_id`),
  CONSTRAINT fk_member_id FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`));

INSERT INTO `members` (`member_id`, `first_name`, `last_name`, `email`, `password`, `parent_id`, `enabled`) VALUES
	(1, 'John', 'Bell', 'john@gmail.com', '$2a$04$5nsfkmBeVSlYemNgYCLrJuv7es5szUiSOaE6f3u7PkIrUqOPU74rS', 0, 'true'),
	(2, 'Bob', 'Funk', 'bob@gmail.com', '$2a$04$5nsfkmBeVSlYemNgYCLrJuv7es5szUiSOaE6f3u7PkIrUqOPU74rS', 1, 'true'),
	(3, 'Anton', 'Smith', 'anton@gmail.com', '$2a$04$5nsfkmBeVSlYemNgYCLrJuv7es5szUiSOaE6f3u7PkIrUqOPU74rS', 1, 'true');

INSERT INTO `member_roles` (`member_role_id`, `member_id`, `role`) VALUES
	(1, 1, "ROLE_ADMIN"),
        (2, 2, "ROLE_USER"),
        (3, 3, "ROLE_USER");
