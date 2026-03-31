package org.datasphere.model;

import org.datasphere.enums.DiaEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AulaPlanejada {

    private AulaModel aulaModel;
    private TopicoModel topicoModel;
    private DiaModel diaModel;
    private LocalDate data;

    public AulaPlanejada() {
    }

    public AulaPlanejada(AulaModel aulaModel, TopicoModel topicoModel, DiaModel diaModel, LocalDate data) {
        this.aulaModel = aulaModel;
        this.topicoModel = topicoModel;
        this.diaModel = diaModel;
        this.data = data;
    }

    public AulaPlanejada(DiaModel diaModel, TopicoModel topicoModel, AulaModel aulaModel) {
        this.diaModel = diaModel;
        this.topicoModel = topicoModel;
        this.aulaModel = aulaModel;
    }

    public AulaModel getAulaModel() {
        return aulaModel;
    }

    public void setAulaModel(AulaModel aulaModel) {
        this.aulaModel = aulaModel;
    }

    public TopicoModel getTopicoModel() {
        return topicoModel;
    }

    public void setTopicoModel(TopicoModel topicoModel) {
        this.topicoModel = topicoModel;
    }

    public DiaModel getDiaModel() {
        return diaModel;
    }

    public void setDiaModel(DiaModel diaModel) {
        this.diaModel = diaModel;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isDiaLetivo() {
        return diaModel.getTipo() != DiaEnum.NAO_LETIVO;
    }

}