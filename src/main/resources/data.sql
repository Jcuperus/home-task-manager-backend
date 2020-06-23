SET foreign_key_checks = 0;
TRUNCATE task_group_app_user;
TRUNCATE task;
TRUNCATE app_user;
TRUNCATE task_group;
SET foreign_key_checks = 1;

INSERT INTO app_user(id, username, password) VALUES (1, 'jaep', '$2a$10$3XyRxmN3ihSogstuLxwAGODQFZyw8zAOcMQ86JxyKT0gvsO5LThPG');

INSERT INTO task_group(id, name) VALUES
    (1, 'Home'),
    (2, 'Work');

INSERT INTO task_group_app_user(group_id, user_id) VALUES
    (1, 1),
    (2, 1);

INSERT INTO task(id, group_id, name, description, due_date) VALUES
    (1, 1, 'Dishes', 'Do the dishes', '2020-06-24 12:15:59'),
    (2, 1, 'Vacuum', NULL, '2020-06-24 12:50:43'),
    (3, 2, 'Paperwork', 'Do some paperwork', '2020-06-25 12:05:38');