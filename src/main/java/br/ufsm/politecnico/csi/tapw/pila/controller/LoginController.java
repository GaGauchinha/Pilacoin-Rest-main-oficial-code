package br.ufsm.politecnico.csi.tapw.pila.controller;

import br.ufsm.politecnico.csi.tapw.pila.model.UsuarioModel;
import br.ufsm.politecnico.csi.tapw.pila.servidor.jwt.JWTUtil;
import br.ufsm.politecnico.csi.tapw.pila.servidor.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    final
    UsuarioRepository repositorioUsuario;

    public LoginController(UsuarioRepository repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @PostMapping("api/login")
    public ResponseEntity<Object> auth(@RequestBody UsuarioModel usuario) {
        System.out.println("Username: " + usuario.getUsername());

        try {
            final Authentication authenticaticon = this.authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getSenha()));
            if (authenticaticon.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticaticon);

                System.out.println("*** Generating Authorization Token ***");
                String token = new JWTUtil().geraToken(usuario.getUsername());

                new UsuarioModel();
                UsuarioModel loggedUser;
                loggedUser = this.repositorioUsuario.findByUsername(usuario.getUsername());

                //usuario.setToken(token);
                usuario.setSenha("");
                usuario.setAdmin(loggedUser.isAdmin());
                System.out.println("Is_Admin:" + loggedUser.isAdmin());

                return new ResponseEntity<>(usuario, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Usuário ou senha incorretos", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Usuário ou senha incorretos", HttpStatus.BAD_REQUEST);
    }
}
