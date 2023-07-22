package com.titoosemobor.moviegenius.Entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "profiles")
public class Profiles {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long profile_id;

  private String profile_name;

  private String profile_image;

  private Timestamp created_at;

  public Profiles() {

  }

  public Profiles(String profile_name) {
    this.profile_name = profile_name;
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
}
