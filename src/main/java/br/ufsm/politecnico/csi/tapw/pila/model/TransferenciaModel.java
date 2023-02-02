package br.ufsm.politecnico.csi.tapw.pila.model;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
public class TransferenciaModel {
        @Id
        @GeneratedValue
        private Long id;
        private String assinaturaMestre;
        private String chaveUsuarioDestino;
        private String chaveUsuarioOrigem;
        private String dataTransacao;
        private String idBloco;
        private String noncePila;
        private String status;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getassinaturaMestre() {
            return assinaturaMestre;
        }

        public void setassinaturaMestre(String assinaturaMestre) {
            this.assinaturaMestre = assinaturaMestre;
        }

        public String getChaveUsuarioDestino() {
            return chaveUsuarioDestino;
        }

        public void setChaveUsuarioDestino(String chaveUsuarioDestino) {
            this.chaveUsuarioDestino = chaveUsuarioDestino;
        }

        public String getChaveUsuarioOrigem() {
            return chaveUsuarioOrigem;
        }

        public void setChaveUsuarioOrigem(String chaveUsuarioOrigem) {
            this.chaveUsuarioOrigem = chaveUsuarioOrigem;
        }

        public String getDataTransacao() {
            return dataTransacao;
        }

        public void setDataTransacao(String dataTransacao) {
            this.dataTransacao = dataTransacao;
        }

        public String getIdBloco() {
            return idBloco;
        }

        public void setIdBloco(String idBloco) {
            this.idBloco = idBloco;
        }

        public String getNoncePila() {
            return noncePila;
        }

        public void setNoncePila(String noncePila) {
            this.noncePila = noncePila;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
}
