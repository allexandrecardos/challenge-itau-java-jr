package com.allexacardosjava.itau_java_test.transaction.controller;

import org.springframework.web.bind.annotation.RestController;

import com.allexacardosjava.itau_java_test.transaction.dto.TransactionDTO;
import com.allexacardosjava.itau_java_test.transaction.service.TransactionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

  private final TransactionService transactionService;

  @PostMapping()
  public ResponseEntity<Void> create(@Valid @RequestBody TransactionDTO dto) {
    transactionService.createTransaction(dto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping()
  public ResponseEntity<Void> delete() {
    transactionService.clearTransactions();
    return ResponseEntity.status(HttpStatus.OK).build();
  }

}
