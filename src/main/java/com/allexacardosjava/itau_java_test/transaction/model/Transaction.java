package com.allexacardosjava.itau_java_test.transaction.model;

import java.time.OffsetDateTime;

import com.allexacardosjava.itau_java_test.transaction.dto.TransactionDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {

  public Transaction(TransactionDTO dto) {
    this.value = dto.value;
    this.datetime = dto.datetime;
  }

  private Double value;
  private OffsetDateTime datetime;

}
