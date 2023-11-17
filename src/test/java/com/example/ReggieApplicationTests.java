package com.example;

import com.example.common.LoginEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReggieApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void teststt(){
        String[] a = new String[4];
        a[0] = "111";
        solve(a);
        System.out.println(a[0]);
    }
    void solve(String[] a){
        a[0] = "222";
    }

}
