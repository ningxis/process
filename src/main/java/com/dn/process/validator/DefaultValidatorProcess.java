package com.dn.process.validator;

import com.dn.bean.ProcessApplicationContext;
import com.dn.exception.ServiceExceptionEnum;
import com.dn.exception.ServiceResult;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.rmi.ServerException;

/**
 * @author dingning
 * @date 2022/5/5 18:23
 **/
@Component
public class DefaultValidatorProcess implements ValidatorProcess{
    @SneakyThrows
    @Override
    public ServiceResult process(ProcessApplicationContext context) {
        if("".equals(context.getUserName())){
            throw new ServerException(ServiceExceptionEnum.VALIDATOR.getMsg());
        };
        System.out.println("基础校验成功");
        return ServiceResult.toSuccessResult();
    }
}
