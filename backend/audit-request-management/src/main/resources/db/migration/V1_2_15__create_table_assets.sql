CREATE TABLE IF NOT EXISTS `assets`(
asset_id integer(128) NOT NULL AUTO_INCREMENT,
asset_name varchar(255) NOT NULL,
task_id integer,
request_id integer,
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
is_deleted boolean DEFAULT FALSE,
PRIMARY KEY(asset_id),
FOREIGN KEY(task_id) REFERENCES task(task_id),
FOREIGN KEY(request_id) REFERENCES request(request_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;