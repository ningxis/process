package com.dn.process.validator;

import com.dn.bean.ProcessApplicationContext;
import com.dn.exception.ServiceExceptionEnum;
import com.dn.exception.ServiceResult;
import org.springframework.stereotype.Component;

/**
 * @author dingning
 * @date 2022/5/5 18:23
 **/
@Component
public class DefaultValidatorProcess implements ValidatorProcess{
    @Override
    public ServiceResult process(ProcessApplicationContext context) {
        if("".equals(context.getUserName())){
            return new ServiceResult(ServiceExceptionEnum.VALIDATOR.getMsg(),ServiceExceptionEnum.VALIDATOR.getCode());
        };
        System.out.println("基础校验成功");
        return ServiceResult.toSuccessResult();
    }
}
