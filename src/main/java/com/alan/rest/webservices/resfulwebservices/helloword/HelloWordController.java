package com.alan.rest.webservices.resfulwebservices.helloword;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWordController {

    @GetMapping("/hello-word")
    public String heloWord(){
        return "hello !!";

    }

    @GetMapping("/hello-word-bean")
    public HelloWord heloWordBean(){
        return new HelloWord("Hellowwww");
    }
}
