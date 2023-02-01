//package br.ufsm.politecnico.csi.tapw.pila.servidor.jwt;
//
//import br.ufsm.politecnico.csi.tapw.pila.model.UsuarioModel;
//import br.ufsm.politecnico.csi.tapw.pila.servidor.repository.UsuarioRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UsuarioRepository repositorioUsuario;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        System.out.println("Username: "+username);
//
//        UsuarioModel usuario = repositorioUsuario.findByUsername(username);
//
//        System.out.println(usuario);
//
//        if (usuario == null) {
//            throw new UsernameNotFoundException("Username or Password Not Found");
//        }
//        else {
//            String authtority = "";
//            if (usuario.isAdmin) {
//                authtority = "admin";
//            }
//            System.out.println(authtority);
//
//            return User.withUsername(usuario.getUsername())
//                    .password(usuario.getSenha())
//                    .authorities(authtority)
//                    .build();
//        }
//    }
//}
