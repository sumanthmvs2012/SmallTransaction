package com.transcation.Service;

import com.transcation.DTO.TransferRequestDto;
import com.transcation.Entity.Account;
import com.transcation.Repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferService {

    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public String transferMoney(TransferRequestDto dto) {
        Account from = accountRepository.findById(dto.getFromAccountId())
                .orElseThrow(() -> new RuntimeException("Sender account not found"));

        Account to = accountRepository.findById(dto.getToAccountId())
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        if (from.getBalance().compareTo(dto.getAmount()) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        from.setBalance(from.getBalance().subtract(dto.getAmount()));
        to.setBalance(to.getBalance().add(dto.getAmount()));

        accountRepository.save(from);

        accountRepository.save(to);

        return "Transfer successful";
    }
}
