package com.eric.hellospring.jsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class DemoApplicationJSP {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplicationJSP.class, args);
	}

}
