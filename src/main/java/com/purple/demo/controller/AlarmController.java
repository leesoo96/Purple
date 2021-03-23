package com.purple.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alarm")
public class AlarmController {

    // @Autowired

    @RequestMapping(value="")
    public String alarm() {
        return "/alarm";
    }
}
