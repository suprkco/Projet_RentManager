package com.epf.rentmanager;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({
        "com.epf.rentmanager.service",
        "com.epf.rentmanager.dao",
        "com.epf.rentmanager.persistence"
}) // packages dans lesquels chercher les beans

public class AppConfiguration { }
