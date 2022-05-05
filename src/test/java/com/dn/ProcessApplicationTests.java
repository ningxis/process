package com.dn;

import com.dn.bean.ProcessApplicationContext;
import com.dn.exception.ServiceResult;
import com.dn.service.ActivityServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProcessApplicationTests {

    @Autowired
    private ActivityServiceImpl activityService;

    @Test
    void contextLoads() {
        ProcessApplicationContext context = new ProcessApplicationContext();
        context.setAge(9);
        context.setUserName("长安");
        context.setUserId(11);

        ServiceResult result = activityService.activityTrigger(context);
        System.out.println(result);
    }

}
