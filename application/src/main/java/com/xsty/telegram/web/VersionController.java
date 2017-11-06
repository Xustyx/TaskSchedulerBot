package com.xsty.telegram.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @RequestMapping(value = "/version",method = RequestMethod.GET)
    public ResponseEntity<?> getVersion(){
        return ResponseEntity.ok("1.0.0");
    }
}
