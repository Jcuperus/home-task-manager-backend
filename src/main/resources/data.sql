INSERT INTO task_group(id, name) VALUES (1, "Group 1"),
                                        (2, "Group 2"),
                                        (3, "Group 3");

INSERT INTO task(id, group_id, name, description) VALUES (1, 1, "Feed cat", "Give him the good stuff"),
                                                         (2, 1, "Do the dishes", "Please don't make me do it again"),
                                                         (3, 2, "Do some paperwork", "Boy do I love me some paperwork");