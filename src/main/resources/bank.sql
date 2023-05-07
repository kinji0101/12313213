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
) ;
