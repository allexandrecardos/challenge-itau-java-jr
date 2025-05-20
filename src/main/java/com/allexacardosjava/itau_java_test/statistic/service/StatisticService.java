package com.allexacardosjava.itau_java_test.statistic.service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.allexacardosjava.itau_java_test.transaction.model.Transaction;
import com.allexacardosjava.itau_java_test.transaction.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatisticService {
  private final TransactionRepository repository;

  public DoubleSummaryStatistics getStatistics() {
    List<Transaction> transactions = repository.list();
    OffsetDateTime now = OffsetDateTime.now();

    DoubleSummaryStatistics statistics = transactions
        .stream()
        .filter(transaction -> transaction.getDatetime().isAfter(now.minusSeconds(60)))
        .mapToDouble(Transaction::getValue)
        .summaryStatistics();

    return statistics;
  }
}
