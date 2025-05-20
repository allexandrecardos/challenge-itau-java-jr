package com.allexacardosjava.itau_java_test.transaction.controller;

import org.springframework.web.bind.annotation.RestController;

import com.allexacardosjava.itau_java_test.transaction.dto.TransactionDTO;
import com.allexacardosjava.itau_java_test.transaction.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

@Tag(name = "Transactions")
@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

  private final TransactionService transactionService;

  @PostMapping()
  @Operation(summary = "Create a new transaction", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Transaction details", content = @Content(schema = @Schema(implementation = TransactionDTO.class))))
  @ApiResponses({
      @ApiResponse(responseCode = "201", description = "Transaction successfully created"),
      @ApiResponse(responseCode = "400", description = "Invalid request format (e.g., malformed JSON)"),
      @ApiResponse(responseCode = "422", description = "Validation error in request body")
  })
  public ResponseEntity<Void> create(@Valid @RequestBody TransactionDTO dto) {
    transactionService.createTransaction(dto);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Operation(summary = "Clear all transactions")
  @ApiResponse(responseCode = "200", description = "All transactions successfully deleted")
  @DeleteMapping()
  public ResponseEntity<Void> delete() {
    transactionService.clearTransactions();
    return ResponseEntity.status(HttpStatus.OK).build();
  }

}
