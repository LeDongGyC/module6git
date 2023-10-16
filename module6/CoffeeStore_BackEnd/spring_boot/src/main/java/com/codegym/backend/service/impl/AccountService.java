package com.codegym.backend.service.impl;

import com.codegym.backend.dto.AccountListDTO;
import com.codegym.backend.model.Account;
import com.codegym.backend.repository.IAccountRepository;
import com.codegym.backend.service.IAccountService;
import com.codegym.backend.service.IUserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AccountService implements IAccountService {

    @Autowired
    IAccountRepository accountRepository;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    SpringTemplateEngine templateEngine;

    @Autowired
    IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;


    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    /**
     * ThangLV
     * check input password matches current password
     */
    @Override
    public Boolean checkPassword(String password, String username) {
        String currentPassword = accountRepository.getCurrentPassword(username);
        return passwordEncoder.matches(password, currentPassword);
    }

    /**
     * ThangLV
     * change password
     */
    @Override
    public void changePassword(String password, String username){
        accountRepository.changePassword(password, username);
    }

    @Override
    public Optional<Account> findAccountByUserName(String username) {
        return accountRepository.findAccountByUserName(username);
    }

    @Override
    public Integer findIdByUserName(String username) {
        return accountRepository.findIdByUserName(username);
    }

    @Override
    public String existsByUserName(String username) {
        return accountRepository.existsByUserName(username);
    }

    @Override
    public String existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    @Override
    public void addVerificationCode(String username) throws MessagingException, UnsupportedEncodingException {
        String verificationCode = RandomString.make(64);
        accountRepository.addVerificationCode(verificationCode, username);
        Account account = accountRepository.findAccountByVerificationCode(verificationCode);
        String name = userService.findNameByAccountId(account.getId(), false);
        this.sendVerificationEmailForResetPassWord(name, verificationCode, account.getEmail());
    }

    @Override
    public void sendVerificationEmailForResetPassWord(String name, String randomCode, String email) throws MessagingException, UnsupportedEncodingException {
        String subject = "\uD83D\uDCE2 Xác thực để thay đổi mật khẩu mới \uD83D\uDCE2";
        String confirmUrl = "http://localhost:4200/verify-change-password/" + randomCode;
        Map<String, Object> props = new HashMap<>();
        props.put("name", name);
        props.put("confirmUrl", confirmUrl);

        Context context = new Context();
        context.setVariables(props);
        String mailContent = templateEngine.process("resetPasswordEmail", context);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        helper.setFrom("namhoai2312@gmail.com", "A0622I1_CAFE☕");
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(mailContent, true);
        javaMailSender.send(message);
    }

    @Override
    public Account findAccountByVerificationCode(String code) {
        return accountRepository.findAccountByVerificationCode(code);
    }

    @Override
    public void saveNewPassword(String encryptPassword, String code) {
        accountRepository.saveNewPassword(encryptPassword, code);
    }

    @Override
    public String findChangPassworDateByUserName(String username) {
        return accountRepository.findChangPassworDateByUserName(username);
    }

    public Boolean checkChangePasswordDateByUserName(String username) throws ParseException {
        String changePasswordDate = this.findChangPassworDateByUserName(username);
        long date1 = simpleDateFormat.parse(changePasswordDate).getTime();
        long date2 = new Date(System.currentTimeMillis()).getTime();
        long dif = (date2 - date1) / (1000 * 60 * 60 * 24);
        return dif >= 30;
    }
}
