package com.sfeir.scc.producer.rest.dto;

import java.math.BigDecimal;

public class WithdrawalRequestDTO {

  private BigDecimal amount;
  private String accountIban;
  private String ownerId;


  public WithdrawalRequestDTO() {
  }

  public WithdrawalRequestDTO(String ownerId, String accountIban, BigDecimal amount) {
    this.amount = amount;
    this.accountIban = accountIban;
    this.ownerId = ownerId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getAccountIban() {
    return accountIban;
  }

  public void setAccountIban(String accountIban) {
    this.accountIban = accountIban;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }
}
