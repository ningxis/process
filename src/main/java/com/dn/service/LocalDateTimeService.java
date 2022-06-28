package com.dn.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dn.exception.ServiceExceptionEnum;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

/**
 * @author skyline
 * @version 1.0
 * @description:
 * @date 2022/6/17 17:41
 */
public class LocalDateTimeService {

    public static void main(String[] args) {
        test03();
    }

    private static void test(){
        String time = "2022-06-17 17:44:35";

        String time1 = "2022-06-17";

        String time2 = "17:44:35";



        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss");

        //当前时间 年月日
        LocalDate localDate = LocalDate.now();
        localDate = LocalDate.parse(time1,dtf1);
        System.out.println(dtf1.format(LocalDateTime.now()));
        System.out.println(localDate);

        //当前时间 时分秒
        LocalTime localTime = LocalTime.now();
        localTime = LocalTime.parse(time2,dtf2);
        System.out.println(dtf2.format(LocalDateTime.now()));
        System.out.println(localTime);

        //当前时间 年月日时分秒
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = LocalDateTime.parse(time,dtf);
        System.out.println(dtf.format(LocalDateTime.now()));
        System.out.println(localDateTime);
    }

    private static void test01(){

        //TODO skyline 2022/6/23 基于BeanUtils.copyProperties(entity, robotGuideTourInfoVo);的一个浅拷贝问题打样处理
        String result = "{\n" +
                "\t\t\t\t\"id\": 1,\n" +
                "\t\t\t\t\"createUser\": 1,\n" +
                "\t\t\t\t\"createTime\": \"2022-06-23 14:52:52\",\n" +
                "\t\t\t\t\"updateUser\": 0,\n" +
                "\t\t\t\t\"updateTime\": null,\n" +
                "\t\t\t\t\"mark\": 1,\n" +
                "\t\t\t\t\"companyCode\": \"admin\",\n" +
                "\t\t\t\t\"robotCode\": \"temi_00120525296\",\n" +
                "\t\t\t\t\"mode\": \"跟随模式\",\n" +
                "\t\t\t\t\"name\": \"大厅导览\",\n" +
                "\t\t\t\t\"status\": \"1\",\n" +
                "\t\t\t\t\"guidePointList\": [\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"id\": 1,\n" +
                "\t\t\t\t\t\t\"createUser\": null,\n" +
                "\t\t\t\t\t\t\"createTime\": null,\n" +
                "\t\t\t\t\t\t\"updateUser\": null,\n" +
                "\t\t\t\t\t\t\"updateTime\": null,\n" +
                "\t\t\t\t\t\t\"mark\": 1,\n" +
                "\t\t\t\t\t\t\"point\": null,\n" +
                "\t\t\t\t\t\t\"pointName\": \"测试点1\",\n" +
                "\t\t\t\t\t\t\"status\": \"3\",\n" +
                "\t\t\t\t\t\t\"guideTourId\": 1,\n" +
                "\t\t\t\t\t\t\"taskId\": \"1\",\n" +
                "\t\t\t\t\t\t\"rank\": 1,\n" +
                "\t\t\t\t\t\t\"arriveWords\": \"stay hungry\",\n" +
                "\t\t\t\t\t\t\"leaveWords\": \"stay foolish\",\n" +
                "\t\t\t\t\t\t\"stayTime\": 3\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t]\n" +
                "\t\t\t}";
//        RobotGuideTour entity = JSON.parseObject(result,RobotGuideTour.class);
//        RobotGuideTourInfoVo robotGuideTourInfoVo = new RobotGuideTourInfoVo();
//        BeanUtils.copyProperties(entity, robotGuideTourInfoVo);
//        System.out.println(JSONObject.toJSONString(robotGuideTourInfoVo));
//        String taskString = "[{\"id\":1,\"createdBy\":1,\"createdTime\":\"2021-12-10 17:57:43\",\"updatedBy\":2,\"updatedTime\":\"2022-06-22 11:00:40\",\"remark\":null,\"delFlag\":\"0\",\"businessName\":\"视远业务\",\"businessKey\":\"test\",\"companyCode\":\"admin\",\"commandProcess\":\"[1,2]\"},{\"id\":2,\"createdBy\":1,\"createdTime\":\"2021-12-22 17:50:53\",\"updatedBy\":2,\"updatedTime\":\"2022-06-22 10:57:16\",\"remark\":null,\"delFlag\":\"0\",\"businessName\":\"播放视频\",\"businessKey\":\"showWord\",\"companyCode\":\"admin\",\"commandProcess\":\"[1,1]\"},{\"id\":3,\"createdBy\":2,\"createdTime\":\"2022-06-23 11:34:59\",\"updatedBy\":null,\"updatedTime\":null,\"remark\":null,\"delFlag\":\"0\",\"businessName\":\"测试业务\",\"businessKey\":\"aaa\",\"companyCode\":\"admin\",\"commandProcess\":\"[4]\"}]";
//        List<ActionBusinessVo> taskList = JSON.parseArray(taskString, ActionBusinessVo.class);
//        HashMap<String,ActionBusinessVo> actionBusiness = new HashMap<>();
//        for (ActionBusinessVo actionBusinessVo : taskList) {
//            actionBusiness.put(actionBusinessVo.getId() + "",actionBusinessVo);
//        }
//
//        for (RobotGuidePoint robotGuidePoint : entity.getGuidePointList()) {
//            RobotGuidePointListVo robotGuidePointListVo = new RobotGuidePointListVo();
//            BeanUtils.copyProperties(robotGuidePoint, robotGuidePointListVo);
//            ActionBusinessVo actionBusinessVo = actionBusiness.get(robotGuidePoint.getTaskId());
//            robotGuidePointListVo.setTask(actionBusinessVo);
//            robotGuideTourInfoVo.getGuidePointList().add(robotGuidePointListVo);
//            robotGuidePoint = null;
//        }
//        System.out.println(JSONObject.toJSONString(robotGuideTourInfoVo));
    }

    private static void test02(){
        //首先去redis中获取，没有key值（过期或者没有初始化）
        //去数据库中获取ip配置和时间配置
        String ipList = "[12.24.25.216,12.24.25.217,12.24.25.218,12.24.25.219,12.24.25.210,12.24.25.211,12.24.25.212,12.24.25.213,12.24.25.214]";
        String[] split = ipList.replace("[","").replace("]","").split(",");
        for (String s : split) {
            String redisKey = "IP" + s;
            System.out.println(redisKey);
            //redis 插入
        }
        //TODO skyline 2022/6/24 引入redis，本地测试运行功能实现
        //插入之后进行逻辑判断
    }

    @SuppressWarnings("unchecked")
    private static void test03(){
        //关于map的拷贝问题，如果直接用应用地址指向原有map，后续更改会影响原有map，clone之后是不会影响原有map的
        HashMap<Integer, String> hashMap = (HashMap<Integer, String>) ServiceExceptionEnum.allException.clone();
        hashMap.put(1,"2");
        hashMap.put(2,"2");
        hashMap.put(3,"2");
        hashMap.put(4,"2");
        hashMap.put(5,"2");
        hashMap.put(6,"2");
        hashMap.put(7,"2");
        System.out.println(JSONObject.toJSONString(hashMap));
        System.out.println(JSONObject.toJSONString(ServiceExceptionEnum.allException));
    }

}
