CREATE TABLE  IF NOT EXISTS `request` (
request_id integer  NOT NULL AUTO_INCREMENT,
priority varchar(45) NOT NULL,
union_name varchar(45) NOT NULL,
status varchar(45) NOT NULL,
production_name varchar(100) NOT NULL,
project_name varchar(100) NOT NULL,
talent_name varchar(100) NOT NULL,
contract_no varchar(100) NOT NULL,
request_created_date date NOT NULL,
contract_date date,
created_by varchar(100) NOT NULL,
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_by varchar(100) ,
updated_at TIMESTAMP DEFAULT NULL,
is_deleted boolean DEFAULT false,
PRIMARY KEY(request_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into request (request_id, priority, union_name, status, production_name, talent_name, contract_no, project_name, request_created_date,
 contract_date, created_by) values (1, "HIGH", "DAG", "Completed", "Marvel Studios", "Florence Pugh","Test", "P_123", now(), now(),  "Test")