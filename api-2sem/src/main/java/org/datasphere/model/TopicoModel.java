package org.datasphere.model;

public class TopicoModel {

    private Long id;
    private String titulo;
    private int aulasMinimas;
    private int aulasMaximas;
    private boolean prova;
    private String siglaMateria;

    public TopicoModel() {
    }

    public TopicoModel(String titulo, int aulasMinimas, int aulasMaximas, boolean prova, String siglaMateria) {
        this.titulo = titulo;
        this.aulasMinimas = aulasMinimas;
        this.aulasMaximas = aulasMaximas;
        this.prova = prova;
        this.siglaMateria = siglaMateria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAulasMinimas() {
        return aulasMinimas;
    }

    public void setAulasMinimas(int aulasMinimas) {
        this.aulasMinimas = aulasMinimas;
    }

    public int getAulasMaximas() {
        return aulasMaximas;
    }

    public void setAulasMaximas(int aulasMaximas) {
        this.aulasMaximas = aulasMaximas;
    }

    public boolean isProva() {
        return prova;
    }

    public void setProva(boolean prova) {
        this.prova = prova;
    }

    public String getSiglaMateria() {
        return siglaMateria;
    }

    public void setSiglaMateria(String siglaMateria) {
        this.siglaMateria = siglaMateria;
    }
}