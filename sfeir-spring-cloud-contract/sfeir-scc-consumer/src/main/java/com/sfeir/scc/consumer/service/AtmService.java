package com.sfeir.scc.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sfeir.scc.consumer.model.WithdrawalRequest;
import com.sfeir.scc.consumer.model.WithdrawalResult;
import com.sfeir.scc.consumer.exception.WithdrawalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.text.MessageFormat;

@Service
public class AtmService {

  private static final Logger LOGGER = LoggerFactory.getLogger(AtmService.class);

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  @Value("${service.sfeir-bank.url}")
  private String bankServerUrl;

  public WithdrawalResult withdraw(String ownerId, String accountIban, BigDecimal amount) throws WithdrawalException {
    WithdrawalRequest dto = new WithdrawalRequest(ownerId, accountIban, amount);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<WithdrawalRequest> request = new HttpEntity<>(dto,headers);

    try {
      ResponseEntity<WithdrawalResult>  response = this.restTemplate.exchange(bankServerUrl+"/withdraw", HttpMethod.POST,request, WithdrawalResult.class);
      return response.getBody();
    }
    catch (HttpClientErrorException.BadRequest e) {
      try {
        return objectMapper.readValue( e.getResponseBodyAsString(), WithdrawalResult.class);
      } catch (JsonProcessingException jsonProcessingException) {
        String msg = MessageFormat.format("An error occurred when parsing server response on bad request at: ''{0}''",bankServerUrl);
        LOGGER.error(msg,e);
        throw new WithdrawalException(msg,e);
      }
    }
    catch (RestClientException e) {
      String msg = MessageFormat.format("An error occurred when calling the bank server at: ''{0}''",bankServerUrl);
      LOGGER.error(msg,e);
      throw new WithdrawalException(msg,e);
    }
  }

  public void setBankServerUrl(String bankServerUrl) {
    this.bankServerUrl = bankServerUrl;
  }
}
