package com.hei.school.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "payment")
public class Payment {

  @Id @GeneratedValue private UUID id;

  @Column(nullable = false, unique = true)
  private String paymentId; // identifiant Vola / externe

  @Column(nullable = false)
  private Instant date;

  @Column(nullable = false)
  private String status; // VERIFYING, SUCCEEDED, FAILED

  @Column(nullable = false)
  private double amount;

  @Column(nullable = false)
  private String method;

  // Constructeurs
  public Payment() {}

  public Payment(String paymentId, Instant date, String status, double amount, String method) {
    this.paymentId = paymentId;
    this.date = date;
    this.status = status;
    this.amount = amount;
    this.method = method;
  }

  // Getters & Setters

  public UUID getId() {
    return id;
  }

  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  public Instant getDate() {
    return date;
  }

  public void setDate(Instant date) {
    this.date = date;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }
}
