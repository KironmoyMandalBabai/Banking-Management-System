package net.javaguides.banking.service.impl;

import net.javaguides.banking.Account;
import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.dto.mapper.AccountMapper;
import net.javaguides.banking.repository.AccountRepository;
import net.javaguides.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    private AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
       Account account= AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return  AccountMapper.mapToAccountDto(savedAccount);


    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account =accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exits"));
return  AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account =accountRepository
        .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exits"));
        double total= account.getBalance() +amount;
        account.setBalance(total);
        Account savedAccount =accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {

        Account account =accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exits"));


        if(account.getBalance()<amount){
        throw  new RuntimeException("Insufficien Ballanece");

        }

        double total =account.getBalance() -amount;
        account.setBalance(total);
        Account savedAccount =accountRepository.save(account);

        return  AccountMapper.mapToAccountDto(savedAccount);


    }

    @Override
    public List<AccountDto> getAllAccount() {

        List<Account> accounts =accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account =accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exits"));

        accountRepository.deleteById(id);

    }


}
