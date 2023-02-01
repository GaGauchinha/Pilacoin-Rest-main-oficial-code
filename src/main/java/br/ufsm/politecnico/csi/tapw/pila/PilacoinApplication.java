package br.ufsm.politecnico.csi.tapw.pila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableAsync
@EnableScheduling
@EnableTransactionManagement
@EnableJpaRepositories
@EntityScan("br.ufsm.politecnico.csi.tapw.pila")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class PilacoinApplication {
    public static void main(String[] args) {
        SpringApplication.run(PilacoinApplication.class, args);
    }

}