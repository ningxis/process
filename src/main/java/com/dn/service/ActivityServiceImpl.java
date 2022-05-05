package com.dn.service;

import com.dn.bean.ProcessApplicationContext;
import com.dn.exception.ServiceResult;
import com.dn.process.ActivityInitProcess;
import com.dn.process.DefaultProcessExecutor;
import com.dn.process.PostProcess;
import com.dn.process.completion.CompletionProcess;
import com.dn.process.validator.DefaultValidatorProcess;
import com.dn.process.validator.ValidatorProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

/**
 * @author dingning
 * @date 2022/5/5 17:21
 **/
@Service
public class ActivityServiceImpl {

    @Autowired
    public DefaultProcessExecutor executor;

    @Autowired
    private ApplicationContext context;

    //在容器初始化前处理执行器
    @PostConstruct
    public void init(){
        //前置校验
        executor.addNode(context.getBean(DefaultValidatorProcess.class));
        //
        executor.addNode(context.getBean(PostProcess.class));
        //地址信息补全
        executor.addNode(context.getBean(CompletionProcess.class));

        executor.addNode(context.getBean(ActivityInitProcess.class));
    }


    public ServiceResult activityTrigger(ProcessApplicationContext context){
        ServiceResult result = executor.execute(context);
        return result;
    }



}
