package com.hei.school.api;

import java.time.Instant;

public class PaymentResponse {
  public String id;
  public String verificationStatus; // SUCCEEDED, FAILED, VERIFYING

  public PspPayment pspPayment;

  public static class PspPayment {
    public String id;
    public String pspType;
    public int amount;
    public Instant creationInstant;
  }
}
