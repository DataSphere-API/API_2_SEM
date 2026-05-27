package org.datasphere.model;

public class MateriaModel {
    private String sigla;
    private String titulo;
    private int cargaHoraria;
    private String emailProfessor;

    public MateriaModel() {}

    public MateriaModel(String sigla, String titulo, int cargaHoraria, String emailProfessor) {
        this.sigla = sigla;
        this.titulo = titulo;
        this.cargaHoraria = cargaHoraria;
        this.emailProfessor = emailProfessor;
    }

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public int getCargaHoraria() { return cargaHoraria; }
    public void setCargaHoraria(int cargaHoraria) { this.cargaHoraria = cargaHoraria; }
    public String getEmailProfessor() { return emailProfessor; }
    public void setEmailProfessor(String emailProfessor) { this.emailProfessor = emailProfessor; }
}