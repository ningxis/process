package com.dn.process.completion;

import com.dn.bean.ProcessApplicationContext;
import com.dn.exception.ServiceExceptionEnum;
import com.dn.exception.ServiceResult;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dingning
 * @date 2022/5/5 21:08
 **/
@Component
public class CompletionProcessImpl implements CompletionProcess {
    @Override
    public ServiceResult process(ProcessApplicationContext context) {
        if (context.getItems() == null || context.getItems().isEmpty()) {
            //关联查询数据
            HashMap<String, Object> items = new HashMap<>();
            items.put("数据A", "来自newyork");
            items.put("数据B", "来自zhuzhou");
            items.put("数据C", "来自changsha");
            HashMap<String, Map<String, Object>> address = new HashMap<>();
            address.put("address", items);
            context.setItems(address);
            System.out.println("地址信息补全成功");
        } else {
            System.out.println("地址信息补全失败,已存在数据" + context.getItems());
            throw new RuntimeException(ServiceExceptionEnum.ADDRESS.getMsg());
        }
        return ServiceResult.toSuccessResult();
    }
}
