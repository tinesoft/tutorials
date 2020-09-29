package com.sfeir.scc.consumer.model;

import java.math.BigDecimal;

public class WithdrawalRequest {

  private BigDecimal amount;
  private String accountIban;
  private String ownerId;


  public WithdrawalRequest() {
  }

  public WithdrawalRequest(String ownerId, String accountIban, BigDecimal amount) {
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
