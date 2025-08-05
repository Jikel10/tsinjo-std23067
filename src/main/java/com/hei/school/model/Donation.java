package com.hei.school.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "donation")
public class Donation {

  @Id @GeneratedValue private UUID id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "donor_id")
  private Donor donor;

  @Column(nullable = false)
  private Instant date;

  @Column(nullable = false)
  private double amount;

  @Column(nullable = false)
  private String paymentMethod;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "payment_id", referencedColumnName = "id")
  private Payment payment;

  // Constructeurs
  public Donation() {}

  public Donation(Donor donor, Instant date, double amount, String paymentMethod, Payment payment) {
    this.donor = donor;
    this.date = date;
    this.amount = amount;
    this.paymentMethod = paymentMethod;
    this.payment = payment;
  }

  // Getters & Setters

  public UUID getId() {
    return id;
  }

  public Donor getDonor() {
    return donor;
  }

  public void setDonor(Donor donor) {
    this.donor = donor;
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

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }
}
