package com.HomeTaskManager.HomeTaskManagerBackend.accesingdatamysql;


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
    
    public User(String name, String role) {
      this.name = name;
      this.role = role;
    }

    public void setName(String name){
      this.name = name;
    }

    public void setEmail(String email){
      this.email = email;
    }
}