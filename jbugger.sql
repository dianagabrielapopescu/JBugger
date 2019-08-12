drop database msg_training;

create database msg_training;

use msg_training;
-- create tables
CREATE TABLE `users` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `counter` int(11) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `mobile_number` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`)
);


CREATE TABLE `roles` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;



CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_users_roles_role_id` (`role_id`),
  CONSTRAINT `FK_users_roles_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`ID`),
  CONSTRAINT `FK_users_roles_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `permissions` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;


CREATE TABLE `roles_permissions` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `FK_roles_permissions_permission_id` (`permission_id`),
  CONSTRAINT `FK_roles_permissions_permission_id` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`ID`),
  CONSTRAINT `FK_roles_permissions_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `notifications` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_notifications_user_id` (`user_id`),
  CONSTRAINT `FK_notifications_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=latin1;


CREATE TABLE `bugs` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `version` varchar(255) DEFAULT NULL,
  `targetDate` datetime DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `fixedVersion` varchar(255) DEFAULT NULL,
  `severity` varchar(255) NOT NULL,
  `CREATED_ID` bigint(20) NOT NULL,
  `ASSIGNED_ID` bigint(20) DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY ` FK_bugs_CREATED_ID_idx` (`CREATED_ID`),
  KEY `FK_bugs_ASSIGNED_ID_idx` (`ASSIGNED_ID`),
  CONSTRAINT ` FK_bugs_CREATED_ID` FOREIGN KEY (`CREATED_ID`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_bugs_ASSIGNED_ID` FOREIGN KEY (`ASSIGNED_ID`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=latin1;


CREATE TABLE `comments` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` varchar(1000) NOT NULL,
  `date` datetime NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `bug_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_comments_user_id_idx` (`user_id`),
  KEY `FK_comments_bug_id_idx` (`bug_id`),
  CONSTRAINT `FK_comments_bug_id` FOREIGN KEY (`bug_id`) REFERENCES `bugs` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_comments_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `attachments` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `attContent` varchar(255) NOT NULL,
  `id_bug` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_attachments_id_bug` (`id_bug`),
  CONSTRAINT `FK_attachments_id_bug` FOREIGN KEY (`id_bug`) REFERENCES `bugs` (`ID`)
);