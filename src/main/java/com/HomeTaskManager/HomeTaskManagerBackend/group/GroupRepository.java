package com.HomeTaskManager.HomeTaskManagerBackend.group;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, Long> {

  List<Group> findByName(String name);

  Group findById(long id);
}