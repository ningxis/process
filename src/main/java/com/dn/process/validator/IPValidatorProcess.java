package com.dn.process.validator;

import com.dn.bean.ProcessApplicationContext;
import com.dn.exception.ServiceResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author skyline
 * @version 1.0
 * @description: ip白名单、黑名单校验
 * @date 2022/6/17 9:40
 */
public class IPValidatorProcess implements ValidatorProcess {

    public final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public ServiceResult process(ProcessApplicationContext context) {

        //考虑扩展,增加白名单、黑名单、时间段配置开关,以及开关配置模式 1、只在白名单中才可以访问 2、不在白名单中也可以访问 3、拒绝所有访问
        Map<String, Object> time = context.getItems().get("time");
        try {
            //TODO skyline 2022/6/17 考虑用localdatetime替换原有date
            long startTime = format.parse(time.get("startTime").toString()).getTime();
            long endTime = format.parse(time.get("endTime").toString()).getTime();
            long curTime = format.parse(time.get("curTime").toString()).getTime();
            if (curTime > startTime && curTime < endTime){
                System.out.println("通过时间检验");
            }else{
                return ServiceResult.error("拒绝访问,不在访问时间内");
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("1.23.45.09", "IPWhiteList");
        map.put("1.23.45.03", "IPWhiteList");
        map.put("1.23.45.02", "IPWhiteList");
        map.put("1.23.45.04", "IPWhiteList");
        map.put("1.23.45.05", "IPBlacklist");
        map.put("1.23.45.06", "IPWhiteList");
        //去命中
        Object result = map.get(context.getItems().get("ip").toString());
        if (result == null) {
            return ServiceResult.error("拒绝访问,IP没有访问权限");
        }

        if ("IPBlacklist".equals(result)) {
            System.out.println("黑名单命中");
            return ServiceResult.error("拒绝访问,IP黑名单");
        }
        if ("IPWhiteList".equals(result)) {
            System.out.println("白名单命中");
        }
        System.out.println("校验通过、正常执行");
        return ServiceResult.toSuccessResult();
    }

    public static void main(String[] args) {
        IPValidatorProcess ipValidatorProcess = new IPValidatorProcess();
        Map<String, Map<String, Object>> items = new HashMap<>();
        HashMap<String, Object> timeMap = new HashMap<>();
        timeMap.put("startTime","2022-05-22");
        timeMap.put("endTime","2022-06-25");
        timeMap.put("curTime","2022-6-26");
        Map<String, Object> ipMap = new HashMap<>();
        ipMap.put("ip","1.23.45.05");
        items.put("ip",ipMap);
        items.put("time",timeMap);
        ProcessApplicationContext context = new ProcessApplicationContext();
        context.setItems(items);
        System.out.println(ipValidatorProcess.process(context));
    }
}
