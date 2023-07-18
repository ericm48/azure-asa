package com.molw.greenspring;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@RestController
public class GreenSpringController
{
   private static final Log methIDshowDateTime, methIDgetCurrentDateTime;

    static
    {
        methIDshowDateTime          = LogFactory.getLog(GreenSpringController.class.getName() + ".showDateTime()");
        methIDgetCurrentDateTime    = LogFactory.getLog(GreenSpringController.class.getName() + ".getCurrentDateTime()");
    }

    @RequestMapping("/showDateTime")
    public String showDateTime()
    {
        Log logger = methIDshowDateTime;

        String returnValue = "Greetings from Azure Spring Apps GreenSpringController: ";
        String dateTime = null;

        logger.debug("Begins...");

        dateTime = "No-Date-Ready";
       // dateTime = getCurrentDateTime();

        returnValue = returnValue + " " + dateTime;
        logger.info("returnValueBe: " + returnValue);

        logger.debug("Ends...");

        return (returnValue);
    }

    public String getCurrentDateTime()
    {
        Log logger = methIDgetCurrentDateTime;
        String returnValue = null;

        logger.debug("Begins...");

        returnValue = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());

        logger.debug("Ends...");

        return( returnValue );
    }
}