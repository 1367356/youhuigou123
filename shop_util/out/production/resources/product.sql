-- product 表
DROP TABLE IF EXISTS `product`;
create table product(
  pid int(11) primary key not null auto_increment comment 'id,自增，用于标识商品',
  id varchar(15) not null comment '商品id',
  name varchar(100) not null comment '商品名',
  image  varchar(255) not null comment '商品图片',
  category varchar(100) not null comment '种类',
  price varchar(15) not null comment'价格',
  sale varchar(10) not null comment '售价',
  platform varchar(10) not null comment '平台',
  disSum varchar(10) not null comment '折扣',
  disSize varchar(20) not null comment '降价后价格',
  startTime varchar(12) not null comment '开始时间',
  endTime varchar(12) not null comment '结束时间',
  disherf varchar(255) not null comment '链接'
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;