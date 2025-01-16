package com.ohgiraffers.setion01.springdatajpa.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    private String indexPage(){
        return "main/main";

    }

}