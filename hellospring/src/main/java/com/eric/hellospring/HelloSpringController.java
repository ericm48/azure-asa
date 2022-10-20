package com.eric.hellospring;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@RestController
public class HelloSpringController
{
   private static final Log methIDIndex;

    static
    {
        methIDIndex = LogFactory.getLog(HelloSpringController.class.getName()
                + ".index()");
    }


    @RequestMapping("/")
    public String index()
    {

        Log logger = methIDIndex;

        String returnValue = "Greetings from Azure Spring Apps: ";
        String dateTime = null;

        logger.debug("Begins...");
        //System.out.println("HelloSpringController.index() Begins...");

        dateTime = "No-Date-Ready";
        //dateTime = getCurrentTimeStamp();

        returnValue = returnValue + " " + dateTime;
        logger.info("returnValueBe: " + returnValue);

        //System.out.println("HelloSpringController.index() Ends...");

        logger.debug("Ends...");

        return (returnValue);
    }
    public String getCurrentTimeStamp()
    {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }
}