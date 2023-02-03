package br.ufsm.politecnico.csi.tapw.pila.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class UsuarioModel {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private byte[] chavePublica;
    private byte[] chavePrivada;
    private String nome;
    private String email;
    public boolean isAdmin;
    private String senha;
    private String token;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getChavePublica() {
        return chavePublica;
    }

    public void setChavePublica(byte[] chavePublica) {
        this.chavePublica = chavePublica;
    }

    public byte[] getChavePrivada() {
        return chavePrivada;
    }

    public void setChavePrivada(byte[] chavePrivada) {
        this.chavePrivada = chavePrivada;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
