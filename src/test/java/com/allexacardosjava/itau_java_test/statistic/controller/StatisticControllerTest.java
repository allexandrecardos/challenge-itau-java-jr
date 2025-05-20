package com.allexacardosjava.itau_java_test.statistic.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.DoubleSummaryStatistics;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.allexacardosjava.itau_java_test.statistic.service.StatisticService;

@WebMvcTest(StatisticController.class)
@Import(StatisticControllerTest.TestConfig.class)
class StatisticControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private StatisticService statisticService;

  @TestConfiguration
  static class TestConfig {
    @Bean
    public StatisticService statisticService() {
      return Mockito.mock(StatisticService.class);
    }
  }

  @Test
  void shouldReturn200WithCorrectStatistics() throws Exception {
    DoubleSummaryStatistics stats = new DoubleSummaryStatistics();
    stats.accept(100.0);
    stats.accept(200.0);

    when(statisticService.getStatistics()).thenReturn(stats);

    mockMvc.perform(get("/statistic")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.count").value(2))
        .andExpect(jsonPath("$.sum").value(300.0))
        .andExpect(jsonPath("$.average").value(150.0))
        .andExpect(jsonPath("$.min").value(100.0))
        .andExpect(jsonPath("$.max").value(200.0));
  }
}