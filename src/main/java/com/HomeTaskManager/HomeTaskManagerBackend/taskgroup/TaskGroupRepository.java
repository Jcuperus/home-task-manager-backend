package com.HomeTaskManager.HomeTaskManagerBackend.taskgroup;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TaskGroupRepository extends CrudRepository<TaskGroup, Long>
{
    public List<TaskGroup> findAllByUsers_Username(String username);
}