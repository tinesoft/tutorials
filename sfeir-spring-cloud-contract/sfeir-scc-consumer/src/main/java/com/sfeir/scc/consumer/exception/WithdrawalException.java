package com.sfeir.scc.consumer.exception;

/**
 * Exception thrown when the call to withdraw fails
 */
public class WithdrawalException extends Exception {

  public WithdrawalException(String message, Throwable cause) {
    super(message, cause);
  }
}
