package com.sfeir.scc.producer.rest.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WithdrawalResultDTO {

  private String message;
  private BigDecimal newBalance;
  private List<ErrorDTO> errors = new ArrayList<>();

  public WithdrawalResultDTO() {
  }

  public WithdrawalResultDTO(String message, BigDecimal newBalance) {
    this(message, Collections.emptyList());
    this.newBalance = newBalance;
  }

  public WithdrawalResultDTO(String message, List<ErrorDTO> errors) {
    this.message = message;
    this.errors = errors;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public BigDecimal getNewBalance() {
    return newBalance;
  }

  public void setNewBalance(BigDecimal newBalance) {
    this.newBalance = newBalance;
  }

  public List<ErrorDTO> getErrors() {
    return errors;
  }

  public void setErrors(List<ErrorDTO> errors) {
    this.errors = errors;
  }
}
