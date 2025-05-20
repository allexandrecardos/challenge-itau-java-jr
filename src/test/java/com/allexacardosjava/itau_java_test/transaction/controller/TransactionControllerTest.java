package com.allexacardosjava.itau_java_test.transaction.controller;

import static org.mockito.Mockito.doNothing;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.allexacardosjava.itau_java_test.transaction.dto.TransactionDTO;
import com.allexacardosjava.itau_java_test.transaction.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
@Import(TransactionControllerTest.TestConfig.class)
class TransactionControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private TransactionService transactionService;

  private final ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();

  @TestConfiguration
  static class TestConfig {
    @Bean
    public TransactionService transactionService() {
      return Mockito.mock(TransactionService.class);
    }
  }

  @Test
  void shouldReturn201WhenTransactionIsValid() throws Exception {
    TransactionDTO dto = new TransactionDTO(100.0, OffsetDateTime.now());

    mockMvc.perform(post("/transaction")
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(dto)))
        .andExpect(status().isCreated());
  }

  @Test
  void shouldReturn400WhenJsonIsMalformed() throws Exception {
    mockMvc.perform(post("/transaction")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{invalid json"))
        .andExpect(status().isBadRequest());
  }

  @Test
  void shouldReturn422WhenValidationFails() throws Exception {
    TransactionDTO dto = new TransactionDTO(-100.0, OffsetDateTime.now());

    mockMvc.perform(post("/transaction")
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(dto)))
        .andExpect(status().isUnprocessableEntity());
  }

  @Test
  void shouldReturn200OnDelete() throws Exception {
    doNothing().when(transactionService).clearTransactions();

    mockMvc.perform(delete("/transaction"))
        .andExpect(status().isOk());
  }
}
