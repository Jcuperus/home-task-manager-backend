package com.HomeTaskManager.HomeTaskManagerBackend.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  User findByName(String name);

  User findById(long id);
}