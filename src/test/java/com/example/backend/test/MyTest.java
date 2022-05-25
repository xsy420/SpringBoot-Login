package com.example.backend.test;

import com.example.backend.entity.User;
import org.junit.jupiter.api.Test;

import static com.example.backend.utils.EntityUtil.UpdateAllNotEmpty;

public class MyTest {
    @Test
    void test() {
        User user1 = new User();
        user1.setId(2);
        user1.setUsername("administrator");
        user1.setPassword("125497");
        User user2 = new User();
        user2.setId(2);
        user2.setUsername("admin");
        user2.setPassword(null);

        UpdateAllNotEmpty(user1, user2);

        System.err.println(user1);

        System.err.println(user2);

    }
}
