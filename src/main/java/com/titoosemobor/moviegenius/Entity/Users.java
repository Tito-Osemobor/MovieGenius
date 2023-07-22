package com.titoosemobor.moviegenius.Entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {
  @Id
  @GeneratedValue
  private Long user_id;

  private String email;

  private String password;

  private Timestamp created_at;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List <Profiles> profile;

  public Users() {

  }

  public Users(String email, String password) {
    this.email = email;
    this.password = password;
    this.created_at = new Timestamp(System.currentTimeMillis());
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Timestamp getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Timestamp created_at) {
    this.created_at = created_at;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public Long getUser_id() {
    return user_id;
  }
}
