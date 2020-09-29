package com.sfeir.scc.consumer.service;

import com.sfeir.scc.consumer.model.Error;
import com.sfeir.scc.consumer.model.WithdrawalResult;
import com.sfeir.scc.consumer.exception.WithdrawalException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.stubrunner.StubFinder;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureStubRunner(
  ids = { "com.sfeir:sfeir-scc-producer"},
  stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
@TestPropertySource(properties = { "service.sfeir-bank.url = http://localhost:8080/sfeir-bank"})
public class AtmServiceIT {

  @Autowired
  private AtmService service;

  @Autowired
  private StubFinder stubFinder;

  @BeforeEach
  public void setUp(){
    //we use the stubFinder to find the URL of stub server providing the bank server API
    service.setBankServerUrl(stubFinder.findStubUrl("sfeir-scc-producer").toString());
  }

  @Test
  public void withdraw_when_balance_is_enough_should_succeed() throws WithdrawalException {

    WithdrawalResult result = service.withdraw("rich_user", "LU00 0000 0000 0000 0001", new BigDecimal(2_000_000L));

    assertThat(result).isNotNull();
    assertThat(result.getMessage()).isEqualTo("Withdrawal succeeded");
    assertThat(result.getErrors()).isEmpty();
    assertThat(result.getNewBalance()).isGreaterThanOrEqualTo(BigDecimal.ZERO);

  }

  @Test
  public void withdraw_when_balance_is_insufficient_should_failed() throws WithdrawalException {

    String ownerId = "poor_user";
    String accountIBAN = "LU00 0000 0000 0000 1111";
    WithdrawalResult result = service.withdraw(ownerId, accountIBAN , new BigDecimal(2_000_000L));

    assertThat(result).isNotNull();
    assertThat(result.getMessage()).isEqualTo("Withdrawal rejected");
    assertThat(result.getErrors()).hasSize(1);
    assertThat(result.getErrors())
      .extracting(Error::getScope, Error::getDescription)
      .containsOnly(tuple("InsufficientBalance", String.format("User '%s' has not enough money on account with IBAN '%s'",ownerId,accountIBAN))) ;
  }

}
