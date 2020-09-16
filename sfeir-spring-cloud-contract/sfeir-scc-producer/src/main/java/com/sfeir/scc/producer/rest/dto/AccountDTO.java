package com.sfeir.scc.producer.rest.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class AccountDTO {
  private String iban;
  private String ownerId;
  private BigDecimal balance;

  public AccountDTO() {
  }

  public AccountDTO(String iban, String ownerId, BigDecimal balance) {
    this.iban = iban;
    this.ownerId = ownerId;
    this.balance = balance;
  }

  public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AccountDTO that = (AccountDTO) o;
    return Objects.equals(iban, that.iban);
  }

  @Override
  public int hashCode() {
    return Objects.hash(iban);
  }
}
