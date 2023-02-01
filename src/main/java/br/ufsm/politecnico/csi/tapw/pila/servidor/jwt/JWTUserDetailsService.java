package br.ufsm.politecnico.csi.tapw.pila.servidor.jwt;

import br.ufsm.politecnico.csi.tapw.pila.model.UsuarioModel;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JWTUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new User(email, new BCryptPasswordEncoder().encode("senhacriptografada"),
                new ArrayList<>());
    }
}
