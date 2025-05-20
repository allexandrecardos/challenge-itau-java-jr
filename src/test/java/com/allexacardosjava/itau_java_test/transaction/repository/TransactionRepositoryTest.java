package com.allexacardosjava.itau_java_test.transaction.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;

import com.allexacardosjava.itau_java_test.transaction.model.Transaction;

public class TransactionRepositoryTest {

  @Test
  void shouldSaveTransaction() {
    TransactionRepository repository = new TransactionRepository();
    Transaction transaction = new Transaction(50.0, OffsetDateTime.now());

    repository.save(transaction);

    assertEquals(1, repository.list().size());
    assertEquals(50.0, repository.list().get(0).getValue());
  }

  @Test
  void shouldClearTransactions() {
    TransactionRepository repository = new TransactionRepository();
    repository.save(new Transaction(50.0, OffsetDateTime.now()));
    repository.save(new Transaction(100.0, OffsetDateTime.now()));

    repository.clear();

    assertTrue(repository.list().isEmpty());
  }

}
