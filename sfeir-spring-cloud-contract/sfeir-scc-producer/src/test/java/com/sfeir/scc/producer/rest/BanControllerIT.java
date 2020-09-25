package com.sfeir.scc.producer.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sfeir.scc.producer.rest.controller.BankController;
import com.sfeir.scc.producer.rest.dto.WithdrawalRequestDTO;
import com.sfeir.scc.producer.service.BankService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BankController.class)
@Import(BankService.class)
public class BanControllerIT {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MockMvc mockMvc;


  @Test
  public void withdraw_successful() throws Exception {

    WithdrawalRequestDTO dto = new WithdrawalRequestDTO("rich_user", "LUSFEIR0000002", new BigDecimal(200L));

    mockMvc.perform(post("/withdraw")
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(dto))
    )
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.message", is("Withdrawal succeeded")));
  }

  @Test
  public void withdraw_rejected_user_not_allowed() throws Exception {

    WithdrawalRequestDTO dto = new WithdrawalRequestDTO("unauthorized_user", "LUSFEIR0000002", new BigDecimal(200L));

    mockMvc.perform(post("/withdraw")
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(dto))
    )
      .andExpect(status().isBadRequest())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.message", is("Withdrawal rejected")))
      .andExpect(jsonPath("$.errors[0].scope", is("UnauthorizedAccess")))
      .andExpect(jsonPath("$.errors[0].description", is("User 'unauthorized_user' has no access to account with IBAN 'LUSFEIR0000002'")))
    ;
  }

  @Test
  public void withdraw_rejected_insufficient_balance() throws Exception {

    WithdrawalRequestDTO dto = new WithdrawalRequestDTO("poor_user", "LUSFEIR0000003", new BigDecimal(200L));

    mockMvc.perform(post("/withdraw")
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(dto))
    )
      .andExpect(status().isBadRequest())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.message", is("Withdrawal rejected")))
      .andExpect(jsonPath("$.errors[0].scope", is("InsufficientBalance")))
      .andExpect(jsonPath("$.errors[0].description", is("User 'poor_user' has not enough money on account with IBAN 'LUSFEIR0000003'")))
    ;
  }
}
