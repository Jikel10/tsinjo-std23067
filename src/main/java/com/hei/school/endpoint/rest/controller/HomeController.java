package com.hei.school.endpoint.rest.controller;

import com.hei.school.model.Beneficiary;
import com.hei.school.model.Donation;
import com.hei.school.model.Donor;
import com.hei.school.model.Help;
import com.hei.school.model.Payment;
import com.hei.school.model.enums.VerificationStatus;
import com.hei.school.repository.BeneficiaryRepository;
import com.hei.school.repository.DonationRepository;
import com.hei.school.repository.DonorRepository;
import com.hei.school.repository.HelpRepository;
import com.hei.school.repository.PaymentRepository;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

  private final DonationRepository donationRepository;
  private final HelpRepository helpRepository;
  private final DonorRepository donorRepository;
  private final BeneficiaryRepository beneficiaryRepository;
  private final PaymentRepository paymentRepository;

  public HomeController(
      DonationRepository donationRepository,
      HelpRepository helpRepository,
      DonorRepository donorRepository,
      BeneficiaryRepository beneficiaryRepository,
      PaymentRepository paymentRepository) {
    this.donationRepository = donationRepository;
    this.helpRepository = helpRepository;
    this.donorRepository = donorRepository;
    this.beneficiaryRepository = beneficiaryRepository;
    this.paymentRepository = paymentRepository;
  }

  @GetMapping("/")
  public String home(Model model) {
    List<Donation> donations = donationRepository.findAll();
    List<Help> helps = helpRepository.findAll();

    // Tri commun en ordre anti-chronologique
    List<Object> allEvents = new ArrayList<>();
    allEvents.addAll(donations);
    allEvents.addAll(helps);
    allEvents.sort(
        (a, b) -> {
          Instant dateA = a instanceof Donation ? ((Donation) a).getDate() : ((Help) a).getDate();
          Instant dateB = b instanceof Donation ? ((Donation) b).getDate() : ((Help) b).getDate();
          return dateB.compareTo(dateA); // décroissant
        });

    model.addAttribute("events", allEvents);
    return "home"; // Correspond à home.html
  }

  @PostMapping("/donation")
  @Transactional
  public String createDonation(
      @RequestParam String donorName,
      @RequestParam double amount,
      @RequestParam String paymentMethod) {

    // Crée ou retrouve le Donor
    Donor donor =
        donorRepository
            .findByName(donorName)
            .orElseGet(() -> donorRepository.save(new Donor(donorName)));

    // Crée le Payment lié
    Payment payment = new Payment();
    payment.setVerificationStatus(VerificationStatus.VERIFYING);
    payment.setDate(Instant.now());
    payment = paymentRepository.save(payment);

    // Crée et enregistre le Don
    Donation donation = new Donation(donor, Instant.now(), amount, paymentMethod, payment);
    donationRepository.save(donation);

    return "redirect:/";
  }

  @PostMapping("/help")
  @Transactional
  public String createHelp(
      @RequestParam String beneficiaryName,
      @RequestParam double amount,
      @RequestParam String accidentDescription) {

    // Crée ou retrouve le Bénéficiaire
    Beneficiary beneficiary =
        beneficiaryRepository
            .findByName(beneficiaryName)
            .orElseGet(() -> beneficiaryRepository.save(new Beneficiary(beneficiaryName)));

    // Crée le Payment lié
    Payment payment = new Payment();
    payment.setVerificationStatus(VerificationStatus.VERIFYING);
    payment.setDate(Instant.now());
    payment = paymentRepository.save(payment);

    // Crée et enregistre l’Aide
    Help help = new Help(beneficiary, Instant.now(), amount, accidentDescription, payment);
    helpRepository.save(help);

    return "redirect:/";
  }
}
