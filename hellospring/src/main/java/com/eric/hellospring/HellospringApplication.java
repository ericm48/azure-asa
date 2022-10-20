package com.eric.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HellospringApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(HellospringApplication.class, args);
	}

}

