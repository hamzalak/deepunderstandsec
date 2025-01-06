package com.sec.hamza.deepunderstandsec.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public-products")
public class PublicProducts {

    @GetMapping("list")
    public String getProducts(){
        return "Hello";
    }

}
