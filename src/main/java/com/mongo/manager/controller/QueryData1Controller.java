package com.mongo.manager.controller;

import com.mongo.manager.service.QueryData1Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/aaa")
public class QueryData1Controller {

    @Resource
    private QueryData1Service queryData1Service;

    @GetMapping("")
    public void a(){
        queryData1Service.a();;
    }

}
