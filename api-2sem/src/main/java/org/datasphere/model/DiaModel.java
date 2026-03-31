package org.datasphere.model;

public class DiaModel {

    private LocalDate data;
    private DiaEnum tipo;

    public DiaModel() {
    }

    public DiaModel(LocalDate data, DiaEnum tipo) {
        this.data = data;
        this.tipo = tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public DiaEnum getTipo() {
        return tipo;
    }

    public void setTipo(DiaEnum tipo) {
        this.tipo = tipo;
    }
}
