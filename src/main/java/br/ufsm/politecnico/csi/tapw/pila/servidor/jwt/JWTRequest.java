package br.ufsm.politecnico.csi.tapw.pila.servidor.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JWTRequest implements Serializable {
    private String username;
    private String senha;
}
