package com.guoanfamily.palmsale.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lenovo on 2017/5/25.
 */
@RestController
public class ExceptionController {

    @GetMapping("/admin/api/test")
    public String test(){
        return "ok";
    }


}
