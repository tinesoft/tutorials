package com.sfeir.scc.producer.rest;

import com.sfeir.scc.producer.service.BankService;
import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;


@WebMvcTest
public class AbstractRestContractVerifier {

  @Autowired
  private WebApplicationContext wac;

  @MockBean
  private BankService bankService;

  @BeforeEach
  public void setUp(){
    RestAssuredMockMvc.standaloneSetup(MockMvcBuilders.standaloneSetup(this.wac));

    given(bankService.hasUserAccessToAccount(eq("rich_user"), anyString())).willReturn(true);
    given(bankService.hasUserEnoughMoneyOnAccount(eq("rich_user"), anyString(), any(BigDecimal.class))).willReturn(true);

    given(bankService.hasUserAccessToAccount(eq("poor_user"), anyString())).willReturn(true);
    given(bankService.hasUserEnoughMoneyOnAccount(eq("poor_user"), anyString(), any(BigDecimal.class))).willReturn(false);

  }


}
