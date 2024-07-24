package net.javaguides.banking.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDto {
    private long id;

    private String accountHolderName;
    private  double balance;
}
