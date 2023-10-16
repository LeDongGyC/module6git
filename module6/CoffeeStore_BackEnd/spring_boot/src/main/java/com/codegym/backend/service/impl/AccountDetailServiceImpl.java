package com.codegym.backend.service.impl;

import com.codegym.backend.model.Account;
import com.codegym.backend.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountDetailServiceImpl implements UserDetailsService {
    @Autowired
    IAccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Not found account with username: " + username));
        return AccountDetail.build(account);
    }

    /**
     * ThangLV
     * get username in Token
     */
    public String getCurrentUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }
}

