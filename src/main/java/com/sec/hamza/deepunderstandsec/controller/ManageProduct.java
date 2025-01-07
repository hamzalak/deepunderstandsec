package com.sec.hamza.deepunderstandsec.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("manage")
public class ManageProduct {


    @GetMapping("new-products")
    List<String> getProducts(){
        System.out.println("Endpoint hit!");  // Add this
        return Arrays.asList("Cafe machine","Air pod","mechanic board","Screen") ;
    }

}
