package com.hei.school.service;

import com.hei.school.model.Donation;
import com.hei.school.model.Donor;
import com.hei.school.model.Payment;
import com.hei.school.model.enums.VerificationStatus;
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
      String fullName,
      String email,
      double amount,
      String paymentMethod,
      String paymentId,
      String pspType,
      String pspPaymentId,
      String payerEmail) {

    // Cherche ou crée le donateur
    Donor donor =
        donorRepository
            .findByEmail(email)
            .orElseGet(() -> donorRepository.save(new Donor(fullName, email)));

    Payment payment =
        new Payment(
            paymentId,
            Instant.now(),
            VerificationStatus.VERIFYING,
            amount,
            paymentMethod,
            pspType,
            pspPaymentId,
            payerEmail);
    paymentRepository.save(payment);

    Donation donation = new Donation(donor, Instant.now(), amount, paymentMethod, payment);
    return donationRepository.save(donation);
  }
}
