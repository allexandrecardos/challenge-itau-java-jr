package com.allexacardosjava.itau_java_test.statistic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.allexacardosjava.itau_java_test.transaction.model.Transaction;
import com.allexacardosjava.itau_java_test.transaction.repository.TransactionRepository;

class StatisticServiceTest {

  private TransactionRepository repository;
  private StatisticService service;

  @BeforeEach
  void setup() {
    repository = Mockito.mock(TransactionRepository.class);
    service = new StatisticService(repository);
  }

  @Test
  void shouldCalculateStatisticsForLast60Seconds() {
    OffsetDateTime now = OffsetDateTime.now();

    List<Transaction> transactions = Arrays.asList(
        new Transaction(100.0, now.minusSeconds(30)),
        new Transaction(200.0, now.minusSeconds(10)),
        new Transaction(300.0, now.minusSeconds(61)));

    when(repository.list()).thenReturn(transactions);

    DoubleSummaryStatistics stats = service.getStatistics();

    assertEquals(2, stats.getCount());
    assertEquals(300.0, stats.getSum());
    assertEquals(150.0, stats.getAverage());
    assertEquals(100.0, stats.getMin());
    assertEquals(200.0, stats.getMax());
  }

  @Test
  void shouldReturnEmptyStatisticsWhenNoRecentTransactions() {
    OffsetDateTime now = OffsetDateTime.now();

    List<Transaction> transactions = List.of(
        new Transaction(100.0, now.minusSeconds(61)),
        new Transaction(200.0, now.minusMinutes(2)));

    when(repository.list()).thenReturn(transactions);

    DoubleSummaryStatistics stats = service.getStatistics();

    assertEquals(0, stats.getCount());
    assertEquals(0.0, stats.getSum());
    assertEquals(0.0, stats.getAverage());
    assertEquals(Double.POSITIVE_INFINITY, stats.getMin());
    assertEquals(Double.NEGATIVE_INFINITY, stats.getMax());
  }
}
