package com.allexacardosjava.itau_java_test.transaction.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.allexacardosjava.itau_java_test.transaction.model.Transaction;

@Repository
public class TransactionRepository {

  private List<Transaction> transactions = new ArrayList<>();

  public void save(Transaction transaction) {
    transactions.add(transaction);
  }

  public void clear() {
    transactions.clear();
  }

  public List<Transaction> list() {
    return transactions;
  }
}
