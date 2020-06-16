package com.HomeTaskManager.HomeTaskManagerBackend.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long>
{
    AppUser findUserByUsername(String username);
}