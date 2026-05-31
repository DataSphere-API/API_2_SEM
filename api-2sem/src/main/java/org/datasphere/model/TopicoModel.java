package org.datasphere.model;

public class TopicoModel {

    private Long id;
    private String titulo;
    private Integer aulasNecessarias;
    private Boolean prova;

    public TopicoModel() {
    }

    public TopicoModel(String titulo, Integer aulasNecessarias, Boolean prova) {
        this.titulo = titulo;
        this.aulasNecessarias = aulasNecessarias;
        this.prova = prova;
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

    public Integer getAulasNecessarias() {
        return aulasNecessarias;
    }

    public void setAulasNecessarias(Integer aulasNecessarias) {
        this.aulasNecessarias = aulasNecessarias;
    }

    public Boolean getProva() {
        return prova;
    }

    public void setProva(Boolean prova) {
        this.prova = prova;
    }
}