package com.allexacardosjava.itau_java_test.transaction.dto;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

@Getter
public class TransactionDTO {

  @NotNull(message = "value is required")
  @PositiveOrZero(message = "value must be positive or zero")
  private Double value;

  @NotNull(message = "datetime is required")
  @PastOrPresent(message = "datetime must be in the past or present")
  private OffsetDateTime datetime;

}
