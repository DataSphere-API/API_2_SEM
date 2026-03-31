package org.datasphere.model;

public class TopicoModel {

    private Long id;
    private String titulo;
    private Integer aulasNecessarias;

    public TopicoModel() {
    }

    public TopicoModel(String titulo, Integer aulasNecessarias) {
        this.titulo = titulo;
        this.aulasNecessarias = aulasNecessarias;
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


}