package com.allexacardosjava.itau_java_test.transaction.dto;

import java.util.DoubleSummaryStatistics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransactionStatsDTO {
  private Long count;
  private Double sum;
  private Double average;
  private Double max;
  private Double min;

  public TransactionStatsDTO(DoubleSummaryStatistics statistics) {
    this.count = statistics.getCount();
    this.sum = statistics.getSum();
    this.average = statistics.getAverage();
    this.min = count > 0 ? statistics.getMin() : 0.0;
    this.max = count > 0 ? statistics.getMax() : 0.0;
  }
}
