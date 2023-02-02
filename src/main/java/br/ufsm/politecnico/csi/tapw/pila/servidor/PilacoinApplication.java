package br.ufsm.politecnico.csi.tapw.pila.servidor;

import br.ufsm.politecnico.csi.tapw.pila.JobRunr.MainConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@EnableScheduling
@EnableTransactionManagement
@EnableJpaRepositories("br.ufsm.politecnico.csi.tapw.pila.servidor.repository")
@EntityScan("br.ufsm.politecnico.csi.tapw.pila")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@Import(MainConfig.class)
public class PilacoinApplication {
    public static void main(String[] args) {
        SpringApplication.run(PilacoinApplication.class, args);
    }

}