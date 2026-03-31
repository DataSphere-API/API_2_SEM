package org.datasphere.model;

import java.time.LocalDate;
import java.util.List;

public class SemestreModel {

    private LocalDate diaInicio;
    private LocalDate diaFim;
    private List aulasPlanejadas;

    public SemestreModel() {
    }

    public SemestreModel(LocalDate diaInicio, LocalDate diaFim) {
        this.diaInicio = diaInicio;
        this.diaFim = diaFim;
    }

    public LocalDate getDiaInicio() {
        return diaInicio;
    }

    public void setDiaInicio(LocalDate diaInicio) {
        this.diaInicio = diaInicio;
    }

    public LocalDate getDiaFim() {
        return diaFim;
    }

    public void setDiaFim(LocalDate diaFim) {
        this.diaFim = diaFim;
    }

    public List getAulasPlanejadas() {
        return aulasPlanejadas;
    }

    public void setAulasPlanejadas(List aulasPlanejadas) {
        this.aulasPlanejadas = aulasPlanejadas;
    }
}
