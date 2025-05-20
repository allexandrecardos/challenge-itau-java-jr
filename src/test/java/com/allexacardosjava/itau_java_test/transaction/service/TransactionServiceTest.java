package com.allexacardosjava.itau_java_test.transaction.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.allexacardosjava.itau_java_test.transaction.dto.TransactionDTO;
import com.allexacardosjava.itau_java_test.transaction.model.Transaction;
import com.allexacardosjava.itau_java_test.transaction.repository.TransactionRepository;

public class TransactionServiceTest {
  private TransactionRepository repository;
  private TransactionService service;

  @BeforeEach
  void setup() {
    repository = Mockito.mock(TransactionRepository.class);
    service = new TransactionService(repository);
  }

  @Test
  void shouldCallRepositoryWithConvertedTransaction() {
    OffsetDateTime datetime = OffsetDateTime.now();
    TransactionDTO dto = new TransactionDTO(200.0, datetime);

    service.createTransaction(dto);

    ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);
    verify(repository).save(captor.capture());

    Transaction transaction = captor.getValue();
    assertEquals(200.0, transaction.getValue());
    assertEquals(datetime, transaction.getDatetime());
  }

  @Test
  void shouldClearAllTransactions() {
    service.clearTransactions();
    verify(repository).clear();
  }
}
