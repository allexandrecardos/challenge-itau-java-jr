package com.allexacardosjava.itau_java_test.statistic.controller;

import java.util.DoubleSummaryStatistics;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allexacardosjava.itau_java_test.statistic.dto.StatisticDTO;
import com.allexacardosjava.itau_java_test.statistic.service.StatisticService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/statistic")
@RequiredArgsConstructor
public class StatisticController {

  private final StatisticService statisticService;

  @GetMapping()
  public ResponseEntity<StatisticDTO> listStatistic() {
    DoubleSummaryStatistics statistics = statisticService.getStatistics();

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(new StatisticDTO(statistics));
  }
}
