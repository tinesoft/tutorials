package com.sfeir.scc.producer.rest.controller;

import com.sfeir.scc.producer.rest.dto.ErrorDTO;
import com.sfeir.scc.producer.rest.dto.WithdrawalRequestDTO;
import com.sfeir.scc.producer.rest.dto.WithdrawalResultDTO;
import com.sfeir.scc.producer.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

@RestController
public class BankController {

  @Autowired
  private BankService bankService;

  @GetMapping({"/", "/home"})
  String home(){
    return "Welcome to SFEIR Bank!";
  }

  @PostMapping(value = "/withdraw")
  ResponseEntity<WithdrawalResultDTO> withdraw(@RequestBody WithdrawalRequestDTO request){

    String userId = request.getOwnerId();
    String accountIban = request.getAccountIban();
    BigDecimal amount = request.getAmount();

    if(!bankService.hasUserAccessToAccount(userId, accountIban)) {
      List<ErrorDTO> errors = Arrays.asList(new ErrorDTO("UnauthorizedAccess", MessageFormat.format("User ''{0}'' has no access to account with IBAN ''{1}''", userId, accountIban)));
      return ResponseEntity.badRequest().body(new WithdrawalResultDTO("Withdrawal rejected", errors));
    }

    if(!bankService.hasUserEnoughMoneyOnAccount(userId, accountIban, amount)) {
      List<ErrorDTO> errors = Arrays.asList(new ErrorDTO("InsufficientBalance", MessageFormat.format("User ''{0}'' has not enough money on account with IBAN ''{1}''", userId, accountIban)));
      return ResponseEntity.badRequest().body(new WithdrawalResultDTO("Withdrawal rejected", errors));
    }

    BigDecimal newBalance = bankService.withdraw(userId, accountIban, amount);
    return  ResponseEntity.ok(new WithdrawalResultDTO("Withdrawal succeeded",newBalance));
  }
}
