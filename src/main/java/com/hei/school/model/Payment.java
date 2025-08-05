package com.hei.school.model;

import com.hei.school.model.enums.VerificationStatus;
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
  private VerificationStatus verificationStatus; // VERIFYING, SUCCEEDED, FAILED

  @Column(nullable = false)
  private double amount;

  @Column(nullable = false)
  private String method;

  @Column(nullable = false, unique = true)
  private String pspPaymentId;

  @Column(nullable = false)
  private String pspType;

  @Column(nullable = false)
  private String payerEmail;

  // Constructeurs
  public Payment() {}

  public Payment(
      String paymentId,
      Instant date,
      VerificationStatus verificationStatus,
      double amount,
      String method,
      String pspType,
      String payerEmail) {
    this.paymentId = paymentId;
    this.date = date;
    this.verificationStatus = verificationStatus;
    this.amount = amount;
    this.method = method;
    this.pspType = pspType;
    this.payerEmail = payerEmail;
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

  public VerificationStatus getVerificationStatus() {
    return verificationStatus;
  }

  public void setVerificationStatus(VerificationStatus verificationStatus) {
    this.verificationStatus = verificationStatus;
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

  public String getPspType() {
    return pspType;
  }

  public String getPspPaymentId() {
    return pspPaymentId;
  }

  public void setPspPaymentId(String pspPaymentId) {
    this.pspPaymentId = pspPaymentId;
  }

  public void setPspType(String pspType) {
    this.pspType = pspType;
  }

  public String getPayerEmail() {
    return payerEmail;
  }

  public void setPayerEmail(String payerEmail) {
    this.payerEmail = payerEmail;
  }
}
