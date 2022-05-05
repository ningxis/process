package com.dn.process;

import com.dn.bean.ProcessApplicationContext;
import com.dn.exception.ServiceResult;

/**
 * @author dingning
 * @date 2022/5/5 17:07
 * 流程编排父类接口
 **/
public interface Process {

    ServiceResult process(ProcessApplicationContext context);

}
