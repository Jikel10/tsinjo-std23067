package com.hei.school.service;

import com.hei.school.api.PaymentResponse;
import com.hei.school.api.VolaApiClient;
import com.hei.school.model.Payment;
import com.hei.school.repository.PaymentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class VolaVerifierService {

  private final PaymentRepository paymentRepository;
  private final VolaApiClient volaApiClient;

  @Scheduled(fixedRate = 300000) // toutes les 5 minutes
  public void verifierPaiements() {
    List<Payment> paiements = paymentRepository.findByVerificationStatus("VERIFYING");

    for (Payment p : paiements) {
      try {
        PaymentResponse reponse =
            volaApiClient.getPayment(p.getPayerEmail(), p.getPspType(), p.getPspPaymentId());

        if (!p.getVerificationStatus().equals(reponse.verificationStatus)) {
          p.setVerificationStatus(reponse.verificationStatus);
          paymentRepository.save(p);
          log.info("Paiement {} mis à jour vers {}", p.getId(), reponse.verificationStatus);
        }
      } catch (Exception e) {
        log.error("Erreur lors de la vérification du paiement " + p.getId(), e);
      }
    }
  }
}
