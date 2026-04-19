package org.datasphere.model;

import org.datasphere.enums.DiaEnum;

import java.time.LocalDate;

public class DiaModel {

    private LocalDate data;
    private boolean disponivelParaProva;

    public DiaModel() {
    }

    public DiaModel(LocalDate data, boolean disponivelParaProva) {
        this.data = data;
        this.disponivelParaProva = disponivelParaProva;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean getDisponivelParaProva() {
        return disponivelParaProva;
    }

    public void setDisponivelParaProva(boolean disponivelParaProva) {
        this.disponivelParaProva = disponivelParaProva;
    }

    public boolean isDisponivelParaProva() {
        return disponivelParaProva;
    }
}
