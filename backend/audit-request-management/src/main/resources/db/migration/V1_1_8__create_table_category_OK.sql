CREATE TABLE  IF NOT EXISTS `category`(
category_id integer   NOT NULL AUTO_INCREMENT,
report_type varchar(250) NOT NULL,
owner_id int,
created_by varchar(100) NOT NULL,
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_by varchar(100) ,
updated_at TIMESTAMP DEFAULT NULL,
is_deleted boolean DEFAULT false,
PRIMARY KEY(category_id),
FOREIGN KEY (owner_id) REFERENCES owner(owner_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
