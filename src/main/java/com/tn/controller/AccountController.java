package com.tn.controller;

import com.tn.entity.Account;
import com.tn.rebository.AccountRepository;
import com.tn.req.AccountReq;
import com.tn.req.AccoutSearchReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepo;

    @GetMapping("show")
    public ResponseEntity<?> showAll(){
        List<Account> accounts= accountRepo.findAll();
        log.info("Show: " + accounts);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody AccountReq accountReq) {
        Account account = new Account();
        account.setAccountName(accountReq.getAccountName());
        account.setBirthday(accountReq.getBirthday());
        account.setAddress(accountReq.getAddress());
        accountRepo.save(account);
        log.info("Create successfully!\n " + account);
        return new ResponseEntity<>("Create successfully!\n " + account, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody AccountReq accountReq, @PathVariable Integer id) {
        Account account = accountRepo.findById(id).orElse(null);
        if (account == null) {
            return new ResponseEntity<>("Not found Account with id: " + id, HttpStatus.BAD_REQUEST);
        }
        account.setAccountName(accountReq.getAccountName());
        account.setBirthday(accountReq.getBirthday());
        account.setAddress(accountReq.getAddress());
        accountRepo.save(account);
        return new ResponseEntity<>("Update successfully!\n " + account, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Account account = accountRepo.findById(id).orElse(null);
        if (account == null) {
            return new ResponseEntity<>("Not found Account with id: " + id, HttpStatus.BAD_REQUEST);
        }
        accountRepo.deleteById(id);
        return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<?> search(@RequestBody AccoutSearchReq accoutSearchReq) {
        List<Account> accounts = accountRepo.search(accoutSearchReq.getAccountNameSearch());
        return new ResponseEntity<>(accounts,HttpStatus.OK);
    }
}
