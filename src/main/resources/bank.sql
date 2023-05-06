CREATE TABLE IF NOT EXISTS`bank_system` (
  `card` varchar(50) NOT NULL,
  `name` varchar(45) NOT NULL,
  `emaill` varchar(45) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `phoneNumbe` int DEFAULT NULL,
  `deposit` int DEFAULT NULL,
  `depositRate` int DEFAULT NULL,
  `loan` int DEFAULT NULL,
  `loanRate` int DEFAULT NULL,
  `offer` int DEFAULT NULL,
  PRIMARY KEY (`card`)
)
