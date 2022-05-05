package com.dn.process;

import com.dn.bean.ProcessApplicationContext;
import com.dn.exception.ServiceExceptionEnum;
import com.dn.exception.ServiceResult;
import org.springframework.stereotype.Component;

/**
 * @author dingning
 * @date 2022/5/5 17:26
 **/
@Component
public class PostProcess implements Process {


    @Override
    public ServiceResult process(ProcessApplicationContext context) {
        if(context.getAge()>100){
            return new ServiceResult(ServiceExceptionEnum.INIT.getMsg(),ServiceExceptionEnum.INIT.getCode());
        }
        return ServiceResult.toSuccessResult();
    }
}
