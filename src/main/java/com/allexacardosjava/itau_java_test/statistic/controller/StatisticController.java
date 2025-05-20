package com.allexacardosjava.itau_java_test.statistic.controller;

import java.util.DoubleSummaryStatistics;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allexacardosjava.itau_java_test.statistic.dto.StatisticDTO;
import com.allexacardosjava.itau_java_test.statistic.service.StatisticService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Statistics")
@RestController
@RequestMapping("/statistic")
@RequiredArgsConstructor
public class StatisticController {

  private final StatisticService statisticService;

  @Operation(summary = "Get transaction statistics", responses = {
      @ApiResponse(responseCode = "200", description = "Statistics retrieved successfully", content = @Content(mediaType = "application/json",schema = @Schema(implementation = StatisticDTO.class)))
  })
  @ApiResponse(responseCode = "200", description = "Statistics retrieved successfully")
  @GetMapping()
  public ResponseEntity<StatisticDTO> listStatistic() {
    DoubleSummaryStatistics statistics = statisticService.getStatistics();

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(new StatisticDTO(statistics));
  }
}
