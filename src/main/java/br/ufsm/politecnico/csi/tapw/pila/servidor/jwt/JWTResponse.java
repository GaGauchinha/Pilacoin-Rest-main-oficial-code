package br.ufsm.politecnico.csi.tapw.pila.servidor.jwt;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class JWTResponse {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final boolean admin;
}
