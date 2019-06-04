package com.example.websocket.websockettest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author xw
 * @date 2019/6/4 15:39
 */
@RequestMapping("test")
@Controller
public class TestController {
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("name","SpringBoot");
        return "index";
    }
}
