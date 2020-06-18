package com.HomeTaskManager.HomeTaskManagerBackend.tasks;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long>
{
    public List<Task> findAllByTaskGroup_IdIn(Long[] groupIds);
}
