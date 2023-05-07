CREATE TABLE IF NOT EXISTS`bank_system` (
  `card` varchar(50) NOT NULL,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `account` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `phonenumbe` varchar(45) DEFAULT NULL,
  `deposit` int DEFAULT '0',
  `depositrate` int DEFAULT '0',
  `loan` int DEFAULT '0',
  `loanrate` int DEFAULT '0',
  `offer` int DEFAULT '0',
  PRIMARY KEY (`card`)
);
