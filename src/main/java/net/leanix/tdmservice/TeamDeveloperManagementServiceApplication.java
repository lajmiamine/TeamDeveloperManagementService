package net.leanix.tdmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

@SpringBootApplication
@EnableWebMvc
public class TeamDeveloperManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamDeveloperManagementServiceApplication.class, args);
    }

}
