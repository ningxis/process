package com.dn.process;

import com.dn.bean.ProcessApplicationContext;
import com.dn.exception.ServiceExceptionEnum;
import com.dn.exception.ServiceResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author dingning
 * @date 2022/5/5 17:43
 * 流程编排执行器
 **/
@Component
public class DefaultProcessExecutor {

    public ArrayList<Process> list = new ArrayList<>();

    public void addNode(Process processNode){
        list.add(processNode);
    }

    public ServiceResult execute(ProcessApplicationContext context) {
        ServiceResult result = ServiceResult.toSuccessResult();
        for (Process item : list) {
            try {
                result = item.process(context);
//                if (result == null || !result.isSuccess() || ServiceResultCodeEnum.IDEMPOTENT.getCode().equals(result.getCode())) {
                    return result;
//                }
            } catch (Throwable e) {
                throw e;
            }
        }
        return result;
    }

}
