package com.allexacardosjava.itau_java_test.transaction.dto;

import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(name = "TransactionDTO", description = "DTO representing a single financial transaction with value and datetime")
public class TransactionDTO {

  @NotNull(message = "value is required")
  @PositiveOrZero(message = "value must be positive or zero")
  @Schema(description = "Transaction value", example = "100.50", required = true)
  private Double value;

  @NotNull(message = "datetime is required")
  @PastOrPresent(message = "datetime must be in the past or present")
  @Schema(description = "Date and time of the transaction (ISO 8601 format)", example = "2025-05-20T15:04:00.789-03:00", required = true)
  private OffsetDateTime datetime;

}
