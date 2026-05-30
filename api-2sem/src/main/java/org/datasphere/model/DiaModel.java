package org.datasphere.model;

import java.time.LocalDate;

public class DiaModel {

    private LocalDate data;
    private boolean disponivelParaProva;
    private String titulo;
    private String descricao;

    public DiaModel() {}

    public DiaModel(LocalDate data, boolean disponivelParaProva) {
        this.data = data;
        this.disponivelParaProva = disponivelParaProva;
    }

    public DiaModel(LocalDate data, boolean disponivelParaProva, String titulo, String descricao) {
        this.data = data;
        this.disponivelParaProva = disponivelParaProva;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public boolean getDisponivelParaProva() { return disponivelParaProva; }
    public void setDisponivelParaProva(boolean disponivelParaProva) { this.disponivelParaProva = disponivelParaProva; }
    public boolean isDisponivelParaProva() { return disponivelParaProva; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}