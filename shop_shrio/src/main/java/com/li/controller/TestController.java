package com.li.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    @RequestMapping("/test")
    public String test() {
        System.out.println("login");
        return "login";
    }

    @RequestMapping("/my")
    public ModelAndView test1(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("modelandview");
        mv.addObject("name", "liyafei");

        //设置返回的数据为json类型，也可以不设置，返回对象
        //mv.setView(new MappingJackson2JsonView());
        return mv;
    }
}
