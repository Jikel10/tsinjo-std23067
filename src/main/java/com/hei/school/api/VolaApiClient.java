package com.hei.school.api;

import java.net.URI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class VolaApiClient {

  @Value("${vola.base-url}")
  private String baseUrl;

  @Value("${vola.api-key}")
  private String apiKey;

  private final RestTemplate restTemplate = new RestTemplate();

  public PaymentResponse getPayment(String payerEmail, String pspType, String pspPaymentId) {
    URI uri =
        UriComponentsBuilder.fromHttpUrl(baseUrl + "/payment")
            .queryParam("apiKey", apiKey)
            .queryParam("payerEmail", payerEmail)
            .queryParam("pspType", pspType)
            .queryParam("pspPaymentId", pspPaymentId)
            .build()
            .toUri();

    return restTemplate.getForObject(uri, PaymentResponse.class);
  }
}
