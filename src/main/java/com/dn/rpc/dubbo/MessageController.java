package com.dn.rpc.dubbo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author skyline
 * @version 1.0
 * @description:
 * @date 2023/3/20 16:37
 */
@RestController
public class MessageController {

    @GetMapping("/send")
    public String test(){
        return "你算哪块小饼干!";
    }

}
