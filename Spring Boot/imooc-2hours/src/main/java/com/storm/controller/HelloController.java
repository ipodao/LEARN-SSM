package com.storm.controller;

import com.storm.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@RestController
@Controller
@ResponseBody
@RequestMapping("/hello")
public class HelloController {

    //配置文件里的内容注入
    @Value("${cupSize}")
    private String cupSize;

    @Value("${age}")
    private Integer age;

    @Value("${content}")
    private String content;

    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value = {"/hello", "/hi"}, method = RequestMethod.GET)
    public String say() {
        //Controller配合templates使用
//        return "index";

        //加上ResponseBody可以使用
        return girlProperties.getCupSize();
    }

    @RequestMapping(value = "/{id}/say2", method = RequestMethod.GET)
    public String say2(@PathVariable("id") Integer id) {
        return "id:" + id;
    }

//    @RequestMapping(value = "/say3", method = RequestMethod.GET)
    @GetMapping(value = "/say3")
    public String say3(@RequestParam(value = "id",required = false,defaultValue = "0") Integer myId) {
        return "id:" + myId;
    }
}
