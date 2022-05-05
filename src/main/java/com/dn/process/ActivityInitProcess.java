package com.dn.process;

import com.dn.bean.ProcessApplicationContext;
import com.dn.exception.ServiceExceptionEnum;
import com.dn.exception.ServiceResult;
import org.springframework.stereotype.Component;

/**
 * @author dingning
 * @date 2022/5/5 21:22
 **/
@Component
public class ActivityInitProcess implements Process {
    @Override
    public ServiceResult process(ProcessApplicationContext context) {
        //todo:dingning 2022/5/5 22:37
        if (context == null || context.getItems() == null || context.getUserId() == 0) {
            return new ServiceResult(ServiceExceptionEnum.INIT.getMsg(), ServiceExceptionEnum.INIT.getCode());
        }
        //从缓存中查询数据
        int userId = 111;
        if(userId==context.getUserId()){
            System.out.println("缓存命中用户");
            return new ServiceResult(ServiceExceptionEnum.CACHED.getMsg(), ServiceExceptionEnum.CACHED.getCode());
        }
        //流程处理成功，打印信息
        System.out.println("流程处理成功："+context);
        ServiceResult result = ServiceResult.toSuccessResult();
        result.setData(context);
        return result;
    }
}
