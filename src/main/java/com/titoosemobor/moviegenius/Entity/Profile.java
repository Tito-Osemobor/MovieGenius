package com.titoosemobor.moviegenius.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.sql.Timestamp;

@Entity
@Table(name = "profiles")
@Builder
@AllArgsConstructor
public class Profile {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long profile_id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  @JsonBackReference
  private User user;

  private String profile_name;

  private String profile_image;

  private Timestamp created_at;

  public Profile() {

  }

  public Profile(User user, String profile_name) {
    this.user = user;
    this.profile_name = profile_name;
    this.created_at = new Timestamp(System.currentTimeMillis());
  }

  public Profile(User user, String profile_name, String profile_image) {
    this.user = user;
    this.profile_name = profile_name;
    this.profile_image = profile_image;
    this.created_at = new Timestamp(System.currentTimeMillis());
  }

  public String getProfile_name() {
    return profile_name;
  }

  public void setProfile_name(String profile_name) {
    this.profile_name = profile_name;
  }

  public String getProfile_image() {
    return profile_image;
  }

  public void setProfile_image(String profile_image) {
    this.profile_image = profile_image;
  }

  public Timestamp getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Timestamp created_at) {
    this.created_at = created_at;
  }

  public void setProfile_id(Long profileId) {
    this.profile_id = profileId;
  }

  public Long getProfile_id() {
    return profile_id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
