package com.aula.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.aula.service.ClienteService;

@Configuration
@ComponentScan(basePackageClasses = ClienteService.class)
public class ServiceConfig {

}
