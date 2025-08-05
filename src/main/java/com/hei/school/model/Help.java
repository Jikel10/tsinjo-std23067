package com.hei.school.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "help")
public class Help {

  @Id @GeneratedValue private UUID id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "beneficiary_id")
  private Beneficiary beneficiary;

  @Column(nullable = false)
  private Instant date;

  @Column(nullable = false)
  private double amount;

  @Column(nullable = false, length = 1000)
  private String accidentDescription;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "payment_id", referencedColumnName = "id")
  private Payment payment;

  // Constructeurs
  public Help() {}

  public Help(
      Beneficiary beneficiary,
      Instant date,
      double amount,
      String accidentDescription,
      Payment payment) {
    this.beneficiary = beneficiary;
    this.date = date;
    this.amount = amount;
    this.accidentDescription = accidentDescription;
    this.payment = payment;
  }

  // Getters & Setters

  public UUID getId() {
    return id;
  }

  public Beneficiary getBeneficiary() {
    return beneficiary;
  }

  public void setBeneficiary(Beneficiary beneficiary) {
    this.beneficiary = beneficiary;
  }

  public Instant getDate() {
    return date;
  }

  public void setDate(Instant date) {
    this.date = date;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public String getAccidentDescription() {
    return accidentDescription;
  }

  public void setAccidentDescription(String accidentDescription) {
    this.accidentDescription = accidentDescription;
  }

  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }
}
