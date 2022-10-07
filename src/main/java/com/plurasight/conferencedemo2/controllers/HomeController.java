package com.plurasight.conferencedemo2.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {
    // we can inject our app version which we are created property file into a controller by
    @Value("${app.version}")
    private String appVersion;

    // create a root or default handler fot this by these methods
    @GetMapping
    @RequestMapping("/")// since jakson is our marshaler for objects to JSON
    public Map getStatus(){
        Map map = new HashMap<String,String>();
        map.put("app-version",appVersion);
        return map;
    }
}
