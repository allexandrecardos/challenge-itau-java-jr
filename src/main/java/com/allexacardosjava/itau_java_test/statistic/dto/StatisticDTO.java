package com.allexacardosjava.itau_java_test.statistic.dto;

import java.util.DoubleSummaryStatistics;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StatisticDTO {

  @Schema(description = "Total number of transactions", example = "10")
  private Long count;

  @Schema(description = "Sum of all transaction values", example = "1500.75")
  private Double sum;

  @Schema(description = "Average transaction value", example = "150.07")
  private Double average;

  @Schema(description = "Maximum transaction value", example = "300.50")
  private Double max;

  @Schema(description = "Minimum transaction value", example = "50.00")
  private Double min;

  public StatisticDTO(DoubleSummaryStatistics statistics) {
    this.count = statistics.getCount();
    this.sum = statistics.getSum();
    this.average = statistics.getAverage();
    this.min = count > 0 ? statistics.getMin() : 0.0;
    this.max = count > 0 ? statistics.getMax() : 0.0;
  }
}
