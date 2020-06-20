package com.thoughtworks.capacity.gtb.mvc;

import com.thoughtworks.capacity.gtb.mvc.exception.UserNotFoundException;
import com.thoughtworks.capacity.gtb.mvc.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Map;

@RestController
@Validated
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestBody @Valid Account account) {
        accountService.createAccount(account);
    }

    @GetMapping(value = "/login")
    public Map<Integer, Account> login(@RequestParam  @Size(min = 5, max = 12, message = "用户名不合法")String username,
                                       @RequestParam @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[_])[0-9a-zA-Z_]{5,12}$", message = "密码不合法") String password) {
        if (  accountService.loginCheck( new Account(username, password, "N/A@test.com")) == false) {
            throw new UserNotFoundException("用户名或密码错误");
        } else {
            return accountService.getMap();
        }
    }
}
