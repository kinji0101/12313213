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