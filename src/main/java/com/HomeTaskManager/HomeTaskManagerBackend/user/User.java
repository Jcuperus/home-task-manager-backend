package com.HomeTaskManager.HomeTaskManagerBackend.user;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User
{

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String name;
  private String password;
  private String email;
  private String role;

  protected User() { }
    
    public User(String name, String role, String password, String email) {
      this.name = name;
      this.role = role;
      this.password = password;
      this.email = email;
    }

    public void setName(String name){
      this.name = name;
    }

    public void setEmail(String email){
      this.email = email;
    }
}