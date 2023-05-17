CREATE TABLE IF NOT EXISTS`bank_system` (
  `card` varchar(50) NOT NULL,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `account` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `phonenumber` varchar(45) DEFAULT NULL,
  `deposit` decimal(15,4) DEFAULT '0.0000',
  `depositrate` decimal(15,4) DEFAULT '0.0000',
  `loan` decimal(15,4) DEFAULT '0.0000',
  `loanrate` decimal(15,4) DEFAULT '0.0000',
  `offer` int DEFAULT '0',
  `createdate` datetime DEFAULT NULL,
  PRIMARY KEY (`card`)
);

CREATE TABLE IF NOT EXISTS`loan` (
   `id` int NOT NULL AUTO_INCREMENT,
  `card` varchar(50) DEFAULT NULL,
  `loan` decimal(15,4) DEFAULT '0.0000',
  `loanrate` decimal(15,4) DEFAULT '0.0000',
  `installments` int DEFAULT '0',
  `loantime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `loan_ibfk_1` (`card`),
  CONSTRAINT `loan_ibfk_1` FOREIGN KEY (`card`) REFERENCES `bank_system` (`card`)
);