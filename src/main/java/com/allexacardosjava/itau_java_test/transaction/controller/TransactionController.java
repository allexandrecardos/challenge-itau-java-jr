package com.allexacardosjava.itau_java_test.transaction.controller;

import org.springframework.web.bind.annotation.RestController;

import com.allexacardosjava.itau_java_test.transaction.dto.TransactionDTO;
import com.allexacardosjava.itau_java_test.transaction.dto.TransactionStatsDTO;
import com.allexacardosjava.itau_java_test.transaction.model.Transaction;
import com.allexacardosjava.itau_java_test.transaction.repository.TransactionRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequiredArgsConstructor
public class TransactionController {

  private final TransactionRepository repository;

  @PostMapping("/transaction")
  public ResponseEntity<Void> create(@Valid @RequestBody TransactionDTO entity) {
    Transaction transaction = new Transaction(entity);
    repository.save(transaction);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/statistic")
  public ResponseEntity<TransactionStatsDTO> listStatistic() {
    List<Transaction> transactions = repository.list();
    OffsetDateTime now = OffsetDateTime.now();

    DoubleSummaryStatistics statistics = transactions
        .stream()
        .filter(transaction -> transaction.getDatetime().isAfter(now.minusSeconds(60)))
        .mapToDouble(transaction -> transaction.getValue())
        .summaryStatistics();

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(new TransactionStatsDTO(statistics));
  }

  @DeleteMapping("/transaction")
  public ResponseEntity<Void> delete() {
    repository.clear();
    return ResponseEntity.status(HttpStatus.OK).build();
  }

}
