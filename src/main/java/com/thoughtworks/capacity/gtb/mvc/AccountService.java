package com.thoughtworks.capacity.gtb.mvc;

import com.thoughtworks.capacity.gtb.mvc.exception.UsernameDuplicationException;
import com.thoughtworks.capacity.gtb.mvc.model.Account;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AccountService {
    private Map<Integer, Account> map = new HashMap<>();

    public AccountService() {
        Account account1 = new Account("user1", "password_123", "example@test.com");
        this.map.put( GenerateId(account1), account1);
        Account account2 = new Account("user2", "password_123", "example@test.com");
        this.map.put( GenerateId(account2), account2);
    }

    public void createAccount(Account account) {
        if( map.containsKey( GenerateId(account) ) ) {
            throw new UsernameDuplicationException("用户已存在");
        }
        map.put(GenerateId(account), account);
//        for (Map.Entry<Integer, Account> entry : map.entrySet()) {
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//        }
    }

    public Integer GenerateId(Account account) {
        String id = account.getUsername();
        return id.hashCode();
    }

    public boolean loginCheck(Account account) {
        for (Account value : map.values()) {
            if(value.getUsername().equals( account.getUsername()) &&
                    value.getPassword().equals( account.getPassword() )) {
                return true;
            }
        }
        return false;
    }

    public Map<Integer, Account> getMap() {
        return map;
    }
}
