package com.sfeir.scc.consumer.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WithdrawalResult {

  private String message;
  private BigDecimal newBalance;
  private List<Error> errors = new ArrayList<>();

  public WithdrawalResult() {
  }

  public WithdrawalResult(String message, BigDecimal newBalance) {
    this(message, Collections.emptyList());
    this.newBalance = newBalance;
  }

  public WithdrawalResult(String message, List<Error> errors) {
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

  public List<Error> getErrors() {
    return errors;
  }

  public void setErrors(List<Error> errors) {
    this.errors = errors;
  }
}
