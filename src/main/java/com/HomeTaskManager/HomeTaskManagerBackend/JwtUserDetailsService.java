package com.HomeTaskManager.HomeTaskManagerBackend;

import com.HomeTaskManager.HomeTaskManagerBackend.user.User;

public class JwtUserDetailsService {
    private User user;

    public String getUsername(){
        return user.getName();
    }
    
    public String getPassword(){
        return user.getPass();
    }
}