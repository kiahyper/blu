package com.blubank.doctorappointment;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@EnableAdminServer
@SpringBootApplication
@EnableJpaRepositories("com.blubank.doctorappointment.*")
@EntityScan(basePackages = {"com.blubank.doctorappointment.*"})
@ComponentScan(basePackages = {"com.blubank.doctorappointment.*"})
public class DoctorAppointmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoctorAppointmentApplication.class, args);
    }

}
