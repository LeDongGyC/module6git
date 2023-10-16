package com.codegym.backend.service.impl;

import com.codegym.backend.dto.AccountListDTO;
import com.codegym.backend.model.Account;
import com.codegym.backend.repository.IAccountRepository;
import com.codegym.backend.service.IAccountServiceBao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceBao implements IAccountServiceBao {

    @Autowired
    IAccountRepository accountRepository;

    @Override
    public Integer findIdUserByUserName(String userName) {
        return accountRepository.findIdUserByUserName(userName);
    }

    @Override
    public void addNew(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void updateEmailAccount(String email, String username) {
        accountRepository.updateEmailAccount(email, username);
    }

    @Override
    public List<AccountListDTO> findAllAccount() {
        return accountRepository.findAllAccount();
    }
}
