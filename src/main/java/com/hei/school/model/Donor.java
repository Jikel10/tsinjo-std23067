package com.hei.school.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "donor")
public class Donor {

  @Id @GeneratedValue private UUID id;

  @Column(nullable = false)
  private String fullName;

  @Column(nullable = false, unique = true)
  private String email;

  // Constructeurs
  public Donor() {}

  public Donor(String fullName, String email) {
    this.fullName = fullName;
    this.email = email;
  }

  // Getters & Setters

  public UUID getId() {
    return id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
