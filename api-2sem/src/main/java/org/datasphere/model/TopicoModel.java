package org.datasphere.model;

public class TopicoModel {

    private Long id;
    private String titulo;
    private Integer aulasNecessarias;
    private Boolean prova;
    private Long idMateria;

    public TopicoModel() {
    }

    public TopicoModel(String titulo, Integer aulasNecessarias, Boolean prova, Long idMateria) {
        this.titulo = titulo;
        this.aulasNecessarias = aulasNecessarias;
        this.prova = prova;
        this.idMateria=idMateria;
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

    public Long getIdMateria() {return idMateria;}

    public void setIdMateria(Long idMateria) {this.idMateria = idMateria;}
}