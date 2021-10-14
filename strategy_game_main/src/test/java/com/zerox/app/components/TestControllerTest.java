package com.zerox.app.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: zhuxi
 * @Time: 2021/10/14 15:01
 * @Description: TestController的单元测试
 * @ModifiedBy: zhuxi
 */
@SpringBootTest
public class TestControllerTest {
    @Autowired
    private TestController testController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testTest() {
        try {
            System.out.println(objectMapper.writeValueAsString(testController.test()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
