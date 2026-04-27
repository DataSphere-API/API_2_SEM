package org.datasphere.service;

import org.datasphere.model.DiaModel;
import org.datasphere.model.SemestreModel;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class SemestreService {

    private SemestreModel semestre = new SemestreModel(LocalDate.now(), LocalDate.now().plusMonths(6));

    public void setSemestre(SemestreModel semestre) {
        this.semestre = semestre;
    }

    public SemestreModel getSemestre() {
        return semestre;
    }

    public void criarSemestreComDias(){
        for (LocalDate dia = semestre.getDiaInicio(); !dia.isAfter(semestre.getDiaFim()); dia = dia.plusDays(1)){
            if (!dia.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !dia.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
                semestre.adicionarDias(new DiaModel(LocalDate.of(dia.getYear(),dia.getMonth(),dia.getDayOfMonth()), true));
            }
        }
    }

}
