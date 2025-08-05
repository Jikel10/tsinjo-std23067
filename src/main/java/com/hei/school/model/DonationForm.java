package com.hei.school.model;

import lombok.Data;

@Data
public class DonationForm {
  private String payerEmail;
  private String pspType;
  private String pspPaymentId;
  private Integer amount;
}
