create database onlinebanking;
use onlinebanking;
CREATE TABLE `account` (
  `accountno` bigint NOT NULL,
  `accounttype` varchar(255) DEFAULT NULL,
  `balance` float DEFAULT NULL,
  `cheque` varchar(255) DEFAULT NULL,
  `deposit` varchar(255) DEFAULT NULL,
  `loanstatus` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `transfer` varchar(255) DEFAULT NULL,
  `withdrawl` varchar(255) DEFAULT NULL,
  `userid` int DEFAULT NULL,
  PRIMARY KEY (`accountno`),
  KEY `FKn1bafc2a3wjs4dsu0ke873w0v` (`userid`),
  CONSTRAINT `FKn1bafc2a3wjs4dsu0ke873w0v` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci; 


CREATE TABLE `account_loanid` (
  `account_accountno` bigint NOT NULL,
  `loanid_loanid` int NOT NULL,
  UNIQUE KEY `UK_otx9y1iwuxp7kl3lc7y3fod0o` (`loanid_loanid`),
  KEY `FKeqb32qg3pseomsr6djlhm1rl7` (`account_accountno`),
  CONSTRAINT `FK8nkr0dma3ftcr7nyugo73iduw` FOREIGN KEY (`loanid_loanid`) REFERENCES `loan` (`loanid`),
  CONSTRAINT `FKeqb32qg3pseomsr6djlhm1rl7` FOREIGN KEY (`account_accountno`) REFERENCES `account` (`accountno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci; 


CREATE TABLE `account_transactionid` (
  `account_accountno` bigint NOT NULL,
  `transactionid_transactionid` int NOT NULL,
  UNIQUE KEY `UK_br5weo9sxrl4kv8krsjekuss7` (`transactionid_transactionid`),
  KEY `FKeiq8rpwhgvob8oyhcjpugf7i2` (`account_accountno`),
  CONSTRAINT `FKeiq8rpwhgvob8oyhcjpugf7i2` FOREIGN KEY (`account_accountno`) REFERENCES `account` (`accountno`),
  CONSTRAINT `FKhgxf210pykni96g91jjmmdbf3` FOREIGN KEY (`transactionid_transactionid`) REFERENCES `transaction` (`transactionid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `admin` (
  `email` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `loan` (
  `loanid` int NOT NULL AUTO_INCREMENT,
  `amount` varchar(255) DEFAULT NULL,
  `interest` int NOT NULL,
  `tenure` int NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `accountno` bigint DEFAULT NULL,
  PRIMARY KEY (`loanid`),
  KEY `FKhbo59yuhfkkh5x7oruux4r86e` (`accountno`),
  CONSTRAINT `FKhbo59yuhfkkh5x7oruux4r86e` FOREIGN KEY (`accountno`) REFERENCES `account` (`accountno`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `transaction` (
  `transactionid` int NOT NULL,
  `amount` float NOT NULL,
  `recepientno` int NOT NULL,
  `recipient_name` varchar(255) DEFAULT NULL,
  `timestamp` datetime(6) DEFAULT NULL,
  `accountno` bigint DEFAULT NULL,
  PRIMARY KEY (`transactionid`),
  KEY `FKk2o1dnsi0qc3wgax1cg3lge3x` (`accountno`),
  CONSTRAINT `FKk2o1dnsi0qc3wgax1cg3lge3x` FOREIGN KEY (`accountno`) REFERENCES `account` (`accountno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `user` (
  `userid` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `panno` bigint NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phno` bigint NOT NULL,
  `transactionpassword` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `user_accountno` (
  `user_userid` int NOT NULL,
  `accountno_accountno` bigint NOT NULL,
  UNIQUE KEY `UK_1ij2q5wy8qqaelt2r1okxgsx0` (`accountno_accountno`),
  KEY `FKd3qixcrc582sf15v4iee8nwsa` (`user_userid`),
  CONSTRAINT `FK51h9odxe1p3y3f2anv25ekjmv` FOREIGN KEY (`accountno_accountno`) REFERENCES `account` (`accountno`),
  CONSTRAINT `FKd3qixcrc582sf15v4iee8nwsa` FOREIGN KEY (`user_userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO admin (email, password)
VALUES ('admin@example.com', 'admin')
