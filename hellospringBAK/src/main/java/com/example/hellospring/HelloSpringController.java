package com.example.hellospring;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class HelloSpringController
{

    @RequestMapping("/")
    public String index()
    {
        String returnValue = "Greetings from Azure Spring Apps: ";
        String dateTime = null;

        System.out.println("HelloSpringController.index() Begins...");

        dateTime = "No-Date-Ready";
        //dateTime = getCurrentTimeStamp();

        returnValue = returnValue + " " + dateTime;

        System.out.println(" returnValueBe: " + returnValue);

        System.out.println("HelloSpringController.index() Ends...");

        return (returnValue);
    }
    public String getCurrentTimeStamp()
    {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }
}