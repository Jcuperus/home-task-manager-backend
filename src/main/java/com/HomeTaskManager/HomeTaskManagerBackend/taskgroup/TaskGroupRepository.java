package com.HomeTaskManager.HomeTaskManagerBackend.taskgroup;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TaskGroupRepository extends CrudRepository<TaskGroup, Long> {

    List<TaskGroup> findByName(String name);

    TaskGroup findById(long id);
}