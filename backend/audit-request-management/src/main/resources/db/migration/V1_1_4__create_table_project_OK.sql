CREATE TABLE  IF NOT EXISTS `project`(
project_id integer NOT NULL AUTO_INCREMENT ,
project_name varchar(255) NOT NULL ,
project_number varchar(255) NOT NULL,
production_id int,
created_by varchar(100) NOT NULL,
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_by varchar(100) ,
updated_at TIMESTAMP DEFAULT NULL,
is_deleted boolean DEFAULT false,
PRIMARY KEY(project_id),
FOREIGN KEY(production_id) REFERENCES production(production_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



