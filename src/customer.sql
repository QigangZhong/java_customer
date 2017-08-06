CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `cellphone` varchar(20) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `preference` varchar(100) DEFAULT NULL,
  `type` varchar(40) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

INSERT INTO `testdb`.`customer`
(`id`,
`name`,
`gender`,
`birthday`,
`cellphone`,
`email`,
`preference`,
`type`,
`description`)
VALUES
(null,
'name1',
'男',
curdate(),
'13000000001',
'13000000001@qq.com',
'篮球,足球,乒乓球',
'黄金客户',
'这里是描述信息');