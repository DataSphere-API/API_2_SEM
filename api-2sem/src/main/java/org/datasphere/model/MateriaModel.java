package org.datasphere.model;

public class MateriaModel {

    String sigla;
    String titulo;
    int cargaHoraria;

    public MateriaModel(){}

    public MateriaModel(String sigla, String titulo, int cargaHoraria){
        this.sigla=sigla;
        this.titulo=titulo;
        this.cargaHoraria=cargaHoraria;
    }

    public String getSigla(){return sigla;}
    public void setSigla(String sigla){this.sigla=sigla;}
    public String getTitulo(){return titulo;}
    public void setTitulo(String titulo){this.titulo=titulo;}
    public int getCargaHoraria(){return cargaHoraria;}
    public void setCargaHoraria(int cargaHoraria){this.cargaHoraria=cargaHoraria;}
}


