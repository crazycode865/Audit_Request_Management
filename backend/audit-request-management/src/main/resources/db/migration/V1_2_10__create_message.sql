CREATE TABLE  IF NOT EXISTS `message` (
message_id integer  NOT NULL AUTO_INCREMENT,
task_id integer,
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
message_from integer NOT NULL,
message_to integer NOT NULL,
message_text varchar(256) NOT NULL,
is_deleted boolean DEFAULT false,
is_seen boolean DEFAULT false,
PRIMARY KEY(message_id),
FOREIGN KEY(task_id) REFERENCES task(task_id),
FOREIGN KEY(message_from) REFERENCES owner(owner_id),
FOREIGN KEY(message_to) REFERENCES owner(owner_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;