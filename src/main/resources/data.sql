
create database aliexpress;


CREATE TABLE `myuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id', 
  `account` varchar(128) DEFAULT NULL COMMENT 'account',
  `nickname` varchar(128) DEFAULT NULL COMMENT 'nickname',
  `password`  varchar(128) DEFAULT NULL COMMENT 'password', 
  `role`  int DEFAULT 0 COMMENT 'role 1 manage 2 editor 3 developer',  
 `createtime` varchar(32) default null COMMENT 'CreateTimeStamp',  
 `updatetime` varchar(32) default null COMMENT 'CreateTimeStamp',  
  PRIMARY KEY (`id`),
  UNIQUE KEY `myuser` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COMMENT='beans';
insert into myuser (account,nickname,password,role,createtime)values("admin3","admin3","123456",3,"1525874197000");

insert into myuser (account,nickname,password,role,createtime)values("admin1","admin1","123456",1,"1525874197000");

insert into myuser (account,nickname,password,role,createtime)values("admin2","admin2","123456",2,"1525874197000");


CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id', 
  `name` varchar(128) DEFAULT NULL COMMENT 'name', 
 `createtime` varchar(32) default null COMMENT 'CreateTimeStamp',  
 `updatetime` varchar(32) default null COMMENT 'CreateTimeStamp', 
  PRIMARY KEY (`id`),
  UNIQUE KEY `tag` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000000 DEFAULT CHARSET=utf8mb4 COMMENT='tag';

 
 
 CREATE TABLE  `pic` (
 `idpic` varchar(32) NOT NULL default '' COMMENT 'id', 
 `name` varchar(128) NOT NULL default '' COMMENT 'name', 
 `img` longblob NOT NULL,
 PRIMARY KEY (`idpic`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



 CREATE TABLE  `goodsbasic` (
 `id` int(8) NOT NULL AUTO_INCREMENT COMMENT 'id', 
 `name` varchar(128) NOT NULL default '' COMMENT 'name', 
 `ename` varchar(128) NOT NULL default '' COMMENT 'ename',
 `tagids` varchar(256) default null COMMENT 'tagids', 
 `cost` varchar(32) default null COMMENT 'cost', 
 `weight` varchar(32) default null COMMENT 'weight', 
 `kind` varchar(32) default null COMMENT 'kind', 
 `supplier` varchar(256) default null COMMENT 'supplier', 
 `description` varchar(256) default null COMMENT 'description',
 `idpic` varchar(32) default null COMMENT 'idpic',   
 `createtime` varchar(32) default null COMMENT 'CreateTimeStamp',  
 `updatetime` varchar(32) default null COMMENT 'CreateTimeStamp',  
 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000000 DEFAULT CHARSET=utf8mb4 COMMENT='goodsbasic';



CREATE TABLE `goodsdetails` (
  `id` int(8) NOT NULL  COMMENT 'id', 
  `description` varchar(1024) DEFAULT NULL COMMENT 'description', 
  `idpic1` varchar(32) DEFAULT NULL COMMENT 'idpic1', 
  `idpic2` varchar(32) DEFAULT NULL COMMENT 'idpic2', 
  `idpic3` varchar(32) DEFAULT NULL COMMENT 'idpic3', 
  `idpic4` varchar(32) DEFAULT NULL COMMENT 'idpic4', 
  `idpic5` varchar(32) DEFAULT NULL COMMENT 'idpic5', 
  `idpic6` varchar(32) DEFAULT NULL COMMENT 'idpic6', 
 `createtime` varchar(32) default null COMMENT 'CreateTimeStamp',  
 `updatetime` varchar(32) default null COMMENT 'CreateTimeStamp',   
  PRIMARY KEY (`id`),
  UNIQUE KEY `goodsdetails` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='goodsdetails';



CREATE TABLE `goodsinfo` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` text   COMMENT 'name',
  `ename` text   COMMENT 'ename',
  `declarezh` text   COMMENT 'name',
  `declareen` text   COMMENT 'ename',
  `tagids` varchar(256) DEFAULT NULL COMMENT 'tagids',
  `cost` varchar(32) DEFAULT NULL COMMENT 'cost',
  `weight` varchar(32) DEFAULT NULL COMMENT 'weight',
  `kind` varchar(32) DEFAULT NULL COMMENT 'kind',
  `supplier` longtext DEFAULT NULL COMMENT 'supplier',
  `shop` longtext DEFAULT NULL COMMENT 'shop',
  `multiproduct` longtext DEFAULT NULL COMMENT 'multiproduct:[{name:"",price:"",weight}]',
  `remarks` longtext COMMENT 'remarks',
  `description` longtext COMMENT 'description',
  `idpics` longtext COMMENT 'idpic',
  `createtime` varchar(32) DEFAULT NULL COMMENT 'CreateTimeStamp',
  `updatetime` varchar(32) DEFAULT NULL COMMENT 'CreateTimeStamp',
  `status` int(11) DEFAULT NULL,
  `idpic` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8mb4 COMMENT='goodsinfo';