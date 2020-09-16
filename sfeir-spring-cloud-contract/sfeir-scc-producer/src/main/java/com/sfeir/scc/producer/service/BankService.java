package com.sfeir.scc.producer.service;

import com.sfeir.scc.producer.rest.dto.AccountDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class BankService {

  private static final Map<String, AccountDTO> DB = new HashMap<>();
  static {
    DB.put("tinesoft", new AccountDTO("LUSFEIR0000001", "tinesoft", new BigDecimal(200_000L)));
    DB.put("rich_user", new AccountDTO("LUSFEIR0000002", "rich_user", new BigDecimal(30_000_000_000L)));
    DB.put("poor_user", new AccountDTO("LUSFEIR0000003", "poor_user", new BigDecimal(200L)));

  }

  private static final Pattern SECURITY_CODE_PATTERN = Pattern.compile("[0-9]{3}-[A-Z]{3}-[0-9]{3}");

  public static boolean isSecurityCodeValid(String securityCode){
    return SECURITY_CODE_PATTERN.matcher(securityCode).matches();
  }

  public boolean isUserKnown(String userId){
    return DB.containsKey(userId);
  }

  public boolean hasUserAccessToAccount(String userId, String accountIban){
    return DB.containsKey(userId) && DB.get(userId).getIban().equals(accountIban);
  }

  public boolean hasUserEnoughMoneyOnAccount(String userId, String acccountIban, BigDecimal amount){
    if(!hasUserAccessToAccount(userId, acccountIban) )
      return false;

    AccountDTO account = DB.get(userId);
    BigDecimal reminder = account.getBalance().subtract(amount);
    return reminder.compareTo(BigDecimal.ZERO)> 0;
  }

  public BigDecimal withdraw(String userId, String acccountIban, BigDecimal amount){

    AccountDTO account = DB.get(userId);
    BigDecimal reminder = account.getBalance().subtract(amount);
    account.setBalance(reminder);

    return reminder;
  }

}
