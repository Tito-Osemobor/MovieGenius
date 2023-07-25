package com.titoosemobor.moviegenius.Entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue
  private Long id;

  private String email;

  private String password;

  private Timestamp created_at;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Profile> profiles;

  public User() {

  }

  public User(String email, String password) {
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

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public List<Profile> getProfiles() {
    return profiles;
  }

  public void setProfiles(List<Profile> profiles) {
    this.profiles = profiles;
  }
}
