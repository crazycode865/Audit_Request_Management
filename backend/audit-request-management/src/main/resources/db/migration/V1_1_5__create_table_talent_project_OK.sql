CREATE TABLE  IF NOT EXISTS `talent_project`(
  talent_id integer NOT NULL,
  project_id integer NOT NULL,
  PRIMARY KEY (talent_id,project_id),
  FOREIGN KEY (talent_id) references talent(talent_id),
  FOREIGN KEY (project_id) references project(project_id)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;
