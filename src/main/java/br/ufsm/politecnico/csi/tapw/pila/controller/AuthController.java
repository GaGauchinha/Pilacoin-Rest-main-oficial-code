package br.ufsm.politecnico.csi.tapw.pila.controller;

import br.ufsm.politecnico.csi.tapw.pila.servidor.jwt.JWTRequest;
import br.ufsm.politecnico.csi.tapw.pila.servidor.jwt.JWTResponse;
import br.ufsm.politecnico.csi.tapw.pila.servidor.jwt.JWTTokenUtil;
import br.ufsm.politecnico.csi.tapw.pila.servidor.jwt.JWTUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTTokenUtil jwtTokenUtil;
    @Autowired
    private JWTUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JWTRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getSenha());
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token, false));
    }

    private void authenticate(String username, String senha) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, senha));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("DADOS_ERRADOS", e);
        }
    }
}
