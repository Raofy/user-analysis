package com.jin10;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Airey
 * @date 2020/1/3 11:34
 * ----------------------------------------------
 * TODO
 * ----------------------------------------------
 */
@SpringBootTest
public class YmlTest {


    @Value("${custom.per}")
    private String per;

    @Test
    public void ymlTest(){

        System.out.println(per);

    }

}
