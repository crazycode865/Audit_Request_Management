CREATE TABLE  IF NOT EXISTS  `owner` (
owner_id integer  NOT NULL AUTO_INCREMENT,
owner_name varchar(45),
owner_user_id varchar(45),
created_by varchar(100) NOT NULL,
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_by varchar(100) ,
updated_at TIMESTAMP DEFAULT NULL,
is_deleted boolean DEFAULT false,
PRIMARY KEY(owner_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;