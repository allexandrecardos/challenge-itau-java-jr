package com.allexacardosjava.itau_java_test.transaction.service;

import org.springframework.stereotype.Service;

import com.allexacardosjava.itau_java_test.transaction.dto.TransactionDTO;
import com.allexacardosjava.itau_java_test.transaction.model.Transaction;
import com.allexacardosjava.itau_java_test.transaction.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

  private final TransactionRepository transactionRepository;

  public void createTransaction(TransactionDTO dto) {
    Transaction transaction = new Transaction(dto);
    transactionRepository.save(transaction);
  }

  public void clearTransactions(){
    transactionRepository.clear();
  }
}
