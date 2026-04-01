package org.datasphere.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SemestreModel {

    private LocalDate diaInicio;
    private LocalDate diaFim;
    private List<DiaModel> diasList;

    public SemestreModel() {
    }

    public SemestreModel(LocalDate diaInicio, LocalDate diaFim) {
        this.diaInicio = diaInicio;
        this.diaFim = diaFim;
        this.diasList = new ArrayList<>();
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

    public List<DiaModel> getDiasList() {
        return diasList;
    }

    public void setDiaFim(LocalDate diaFim) {
        this.diaFim = diaFim;
    }

    public void adicionarDias(DiaModel dia){
        diasList.add(dia);
    }
}
