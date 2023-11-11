package com.globant.utils;

import com.globant.pojos.Account;
import com.globant.pojos.User;

import java.util.HashSet;
import java.util.Set;

public class TestUtil {

    public static Set<User> getExampleUsers(int number) {

        Set<User> users = new HashSet<>();
        for (int i = 0; i < number; i++) {
            users.add(new User("Usuario " + i,
                    "Apellido " + i,  "user" + i + "@example.com",
                    "password", new Account(), null));
        }
        return users;
    }

}
