CREATE TABLE  IF NOT EXISTS `talent`(
talent_id integer  NOT NULL,
talent_name varchar(45) NOT NULL,
created_by varchar(100) NOT NULL,
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_by varchar(100) ,
updated_at TIMESTAMP DEFAULT NULL,
is_deleted boolean DEFAULT false,
PRIMARY KEY(talent_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

