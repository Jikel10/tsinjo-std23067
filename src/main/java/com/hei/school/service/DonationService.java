package com.hei.school.service;

import com.hei.school.model.Donation;
import com.hei.school.model.Donor;
import com.hei.school.model.Payment;
import com.hei.school.repository.DonationRepository;
import com.hei.school.repository.DonorRepository;
import com.hei.school.repository.PaymentRepository;
import java.time.Instant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DonationService {

  private final DonationRepository donationRepository;
  private final DonorRepository donorRepository;
  private final PaymentRepository paymentRepository;

  public DonationService(
      DonationRepository donationRepository,
      DonorRepository donorRepository,
      PaymentRepository paymentRepository) {
    this.donationRepository = donationRepository;
    this.donorRepository = donorRepository;
    this.paymentRepository = paymentRepository;
  }

  @Transactional
  public Donation createDonation(
      String fullName, String email, double amount, String paymentMethod, String paymentId) {

    // Cherche ou crée le donateur
    Donor donor =
        donorRepository
            .findByEmail(email)
            .orElseGet(
                () -> {
                  Donor newDonor = new Donor(fullName, email);
                  return donorRepository.save(newDonor);
                });

    // Crée un Payment avec statut VERIFYING (paiement en cours de validation)
    Payment payment = new Payment(paymentId, Instant.now(), "VERIFYING", amount, paymentMethod);
    paymentRepository.save(payment);

    // Crée le don lié
    Donation donation = new Donation(donor, Instant.now(), amount, paymentMethod, payment);
    return donationRepository.save(donation);
  }
}
