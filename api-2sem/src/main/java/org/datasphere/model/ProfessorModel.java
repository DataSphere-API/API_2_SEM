package org.datasphere.model;

import org.datasphere.service.SenhaHashService;

public class ProfessorModel {

    private String email;
    private String nome;
    private String senha;
    private boolean coordenador;

    public ProfessorModel() {
    }

    public ProfessorModel(String email, String nome, String senha, boolean coordenador) {
        this.email = email;
        this.nome = nome;
        this.senha = SenhaHashService.gerarHashSenha(senha);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = SenhaHashService.gerarHashSenha(senha);
    }

    public boolean isCoordenador() {
        return coordenador;
    }

    public void setCoordenador(boolean coordenador) {
        this.coordenador = coordenador;
    }
}