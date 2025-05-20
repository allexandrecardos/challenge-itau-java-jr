package com.allexacardosjava.itau_java_test.transaction.model;

import java.time.OffsetDateTime;

import com.allexacardosjava.itau_java_test.transaction.dto.TransactionDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Transaction {

  private Double value;
  private OffsetDateTime datetime;

  public Transaction(TransactionDTO dto) {
    this.value = dto.getValue();
    this.datetime = dto.getDatetime();
  }

}
