package com.dn.service;

import com.dn.bean.ProcessApplicationContext;
import com.dn.exception.ServiceResult;
import com.dn.process.ActivityInitProcess;
import com.dn.process.DefaultProcessExecutor;
import com.dn.process.PostProcess;
import com.dn.process.completion.CompletionProcess;
import com.dn.process.validator.DefaultValidatorProcess;
import com.dn.process.validator.IPValidatorProcess;
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
        //todo:dingning 2022/5/5 22:45
        // 1、check参数  2、前置处理  3、触发  4、后置处理  数据分页in
        //todo:dingning 2022/5/5 23:04   图化链路调用，线程池处理

        //校验入参是否正确，参数补全，触发操作，后置处理

        //前置校验
        executor.addNode(context.getBean(DefaultValidatorProcess.class));
        //
        executor.addNode(context.getBean(PostProcess.class));
        //地址信息补全
        executor.addNode(context.getBean(CompletionProcess.class));

        executor.addNode(context.getBean(ActivityInitProcess.class));
        executor.addNode(context.getBean(IPValidatorProcess.class));
    }


    public ServiceResult activityTrigger(ProcessApplicationContext context){
        ServiceResult result = executor.execute(context);
        return result;
    }



}
