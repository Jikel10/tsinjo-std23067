package com.hei.school.service;

import com.hei.school.model.Payment;
import com.hei.school.repository.PaymentRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VolaVerifierService {

  private static final Logger logger = LoggerFactory.getLogger(VolaVerifierService.class);

  private final PaymentRepository paymentRepository;
  private final RestTemplate restTemplate;

  // URL base de l’API Vola (à configurer dans application.yml)
  @Value("${vola.api.url}")
  private String volaApiUrl;

  // Clé API pour authentification
  @Value("${vola.api.key}")
  private String apiKey;

  public VolaVerifierService(PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
    this.restTemplate = new RestTemplate();
  }

  @Scheduled(fixedDelay = 60000) // Toutes les 60 secondes
  public void verifyPayments() {
    logger.info("Démarrage de la vérification des paiements en état VERIFYING...");

    List<Payment> pendingPayments = paymentRepository.findByStatus("VERIFYING");

    for (Payment payment : pendingPayments) {
      try {
        String url = volaApiUrl + "/payments/" + payment.getPaymentId();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<VolaPaymentStatusResponse> response =
            restTemplate.exchange(url, HttpMethod.GET, entity, VolaPaymentStatusResponse.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
          String status = response.getBody().getStatus();

          if (!status.equals(payment.getStatus())) {
            logger.info(
                "Mise à jour du paiement {}: {} -> {}",
                payment.getPaymentId(),
                payment.getStatus(),
                status);
            payment.setStatus(status);
            paymentRepository.save(payment);
          }
        } else {
          logger.warn(
              "Réponse inattendue pour le paiement {}: status HTTP {}",
              payment.getPaymentId(),
              response.getStatusCode());
        }

      } catch (Exception e) {
        logger.error("Erreur lors de la vérification du paiement " + payment.getPaymentId(), e);
      }
    }
  }

  // Classe interne pour parser la réponse JSON de Vola (adapter selon la vraie réponse API)
  public static class VolaPaymentStatusResponse {
    private String status;

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }
  }
}
