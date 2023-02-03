package br.ufsm.politecnico.csi.tapw.pila.servidor.interfaces;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

public interface WebSecurityConfigInterface {
    void configure(HttpSecurity http) throws Exception;
    void addCorsMappings(CorsRegistry registry);
}
