CREATE TABLE  IF NOT EXISTS `request_schedule`(
request_schedule_id integer NOT NULL AUTO_INCREMENT,
request_id integer,
request_created date NOT NULL,
expected_closure date,
audit_start_date date,
audit_end_date date,
report_submission date,
settlement_date date,
receipt_date date,
created_by varchar(100) NOT NULL,
created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_by varchar(100) ,
updated_at TIMESTAMP DEFAULT NULL,
is_deleted boolean DEFAULT false,
PRIMARY KEY(request_schedule_id),
FOREIGN KEY(request_id) REFERENCES request(request_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;