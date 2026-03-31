package org.datasphere.model;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class AulaModel {

    private DayOfWeek diaDaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFim;

    public AulaModel(DayOfWeek diaDaSemana, LocalTime horaInicio, LocalTime horaFim) {
        this.diaDaSemana = diaDaSemana;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public AulaModel() {
    }

    public DayOfWeek getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(DayOfWeek diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }
}