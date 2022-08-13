UPDATE request
SET created_by = 'chris',
project_name = 'Avengers - End Game',
contract_no = 'M525T8523045'
WHERE request_id = 1;

UPDATE task
SET created_by = 'chris'
WHERE task_id = 1;

UPDATE task
SET created_by = 'chris'
WHERE task_id = 2;

UPDATE owner
SET owner_user_id = 'brian'
WHERE owner_id = 10;

UPDATE owner
SET owner_user_id = 'chris',
owner_name = 'Chris Boris'
WHERE owner_id = 11;

UPDATE category
SET owner_id = 10
WHERE category_id = 2002;