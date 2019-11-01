package com.alan.rest.webservices.resfulwebservices.helloword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWordController {

    @Autowired
    public MessageSource messageSource;
    
    @GetMapping("/hello-word")
    public String heloWord(){
        return "hello !!";

    }

    @GetMapping("/hello-word-bean")
    public HelloWord heloWordBean(){
        return new HelloWord("Hellowwww");
    }

    @GetMapping("/hello-word-internationalized")
    public String heloWordInternacionalized(){
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }
}
