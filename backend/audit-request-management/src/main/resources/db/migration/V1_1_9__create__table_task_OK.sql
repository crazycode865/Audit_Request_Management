CREATE TABLE  IF NOT EXISTS `task` (
task_id integer  NOT NULL AUTO_INCREMENT,
request_id integer,
category_id integer,
audit_start_date date NOT NULL,
audit_end_date date NOT NULL,
closed_at TIMESTAMP DEFAULT NULL,
created_by varchar(100) NOT NULL,
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_by varchar(100) ,
updated_at TIMESTAMP DEFAULT NULL,
is_deleted boolean DEFAULT false,
PRIMARY KEY(task_id),
FOREIGN KEY(request_id) REFERENCES request(request_id),
FOREIGN KEY(category_id) REFERENCES category(category_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;