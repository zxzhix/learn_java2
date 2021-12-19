create table User
(
UserCode varchar(20) primary key comment '用户编码，唯一',
UserName varchar(40) not null comment '用户姓名',
Password varchar(40) not null comment '用户密码',
Sex	     varchar(1)  not null comment '性别0-女、1-男、2-其他',
Birthday Date 	comment '生日',
State	 varchar(1) not null comment '用户状态1-有效、2-无效',
MakeDate Date not null comment '创建时间',
ModifyDate Date not null comment '修改时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

	
create table Commodity
(
CommID varchar(20) primary key  comment '商品编码、唯一',
Commname varchar(40) not null comment '商品名称',
Price varchar(40) not null comment '单价',
Count Int(10)	not null comment '数量',
EffectStartDate	    date  comment '有效起期',
EffectEndDate       date	 comment '有效止期',
state	 varchar(1) not null comment '用户状态1-有效、2-无效',
makedate date not null comment '创建时间',
modifydate date not null comment '修改时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
	
create table OrderMain
(
OrderMainID varchar(20) primary key  comment '商品编码、唯一',
UserCode varchar(40) not null comment '用户编码',
TotalMoney varchar(40) not null comment '总价',
Count Int(10)	not null  comment '数量',
state	 varchar(1) not null comment '订单状态1-有效、2-无效',
makedate date not null comment '创建时间',
modifydate date not null comment '修改时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE OrderMain ADD INDEX OrderMain_1 (UserCode);


create table OrderSubmeter
(
SerialNo varchar(30) primary key comment '订单流水号、唯一',
OrderMainID varchar(40) not null comment '订单主表ID',
CommID varchar(20) not null comment '商品编码',
Price varchar(40) not null comment '单价',
Count Int(10)	not null comment '购买数量',
state	 varchar(1) not null comment '订单状态1-有效、2-无效',
makedate date not null comment '创建时间',
modifydate date not null comment '修改时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE OrderSubmeter ADD INDEX OrderSubmeter_1 (OrderMainID,CommID);
			
  			