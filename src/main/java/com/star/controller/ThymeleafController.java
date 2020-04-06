package com.star.controller;

import com.star.service.StudentService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;


/**
 * @author duxing
 * thymeleaf 模板使用
 * @version V1.0
 * @Package com.star.controller
 * @date 2020/04/01 22:22
 */
@Log4j
@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String getTest(Model m){
        log.error("模拟测试thymeleaf成功！！！");
        List<Map<String, Object>> list = this.studentService.queryStudentListMap();
        m.addAttribute("list",list);
        m.addAttribute("index","第一次使用thymeleaf");
        return  "hello";
    }


}    