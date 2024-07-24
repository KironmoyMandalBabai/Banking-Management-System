package net.javaguides.banking.repository;

import net.javaguides.banking.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {



}
