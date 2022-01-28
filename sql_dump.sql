-- Database export via SQLPro (https://www.sqlprostudio.com/allapps.html)
-- Exported by geethyapa at 22-09-2019 20:08.
-- WARNING: This file may contain descructive statements such as DROPs.
-- Please ensure that you are running the script at the proper location.

-- BEGIN TABLE users
DROP TABLE IF EXISTS users;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `points` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- BEGIN TABLE tasks
DROP TABLE IF EXISTS tasks;
CREATE TABLE `tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `task` varchar(256) NOT NULL,
  `date` datetime NOT NULL,
  `comment` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_userid` (`userid`),
  CONSTRAINT `fk_userid` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

-- Inserting 11 rows into tasks
-- Insert batch #1
INSERT INTO tasks (id, userid, task, date, comment) VALUES
(8, 1, 'I restored a broken equipment', '2019-09-22 12:42:29', 'need help\n'),
(9, 1, 'I cleaned the trash', '2019-09-22 12:49:41', 'successful'),
(10, 2, 'I cleaned graffiti on walls', '2019-09-22 13:36:04', 'Colorwashed the walls'),
(11, 2, 'I planted a tree', '2019-09-22 13:36:38', 'I planted a neem tree'),
(12, 2, 'I cleaned the benches', '2019-09-22 13:48:13', 'washed the benches'),
(13, 1, 'I cleaned graffiti on walls', '2019-09-22 14:13:59', 'fhyxha'),
(14, 2, 'I cleaned the benches', '2019-09-22 14:18:00', 'jjdn jdnd'),
(15, 1, 'I restored a broken equipment', '2019-09-22 14:24:00', 'blah'),
(16, 2, 'I planted a tree', '2019-09-22 14:26:39', 'thj'),
(17, 1, 'I Planted a tree', '2019-09-22 14:35:16', 'planted a weed tree'),
(18, 1, 'I Planted a tree', '2019-09-22 14:36:19', 'planted a weed tree');

-- END TABLE tasks



-- Inserting 3 rows into users
-- Insert batch #1
INSERT INTO users (id, name, username, password, points) VALUES
(1, 'Geeth Wihanga', 'geeth', '123', 41),
(2, 'Oshan Fernando', 'oshan', '456', 22),
(3, 'Sreen Yapa', 'sreen', 'abc', 0);

-- END TABLE users

