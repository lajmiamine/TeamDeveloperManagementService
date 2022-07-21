package net.leanix.tdmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
        //(exclude = SecurityAutoConfiguration.class)
@EnableWebMvc
public class TeamDeveloperManagementServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamDeveloperManagementServiceApplication.class, args);
    }

}
