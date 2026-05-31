package org.datasphere.service;

import org.datasphere.dao.interfaces.IDAO;
import org.datasphere.model.*;
import org.datasphere.dao.AulaPlanejadaDAO;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

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

    public void criarSemanaSprint(LocalDate dataInicial, LocalDate dataFinal) {
        for (DiaModel dia : getSemestre().getDiasList()) {
            LocalDate dataAtual = dia.getData();

            if (!dataAtual.isBefore(dataInicial) && !dataAtual.isAfter(dataFinal)) {
                dia.setDisponivelParaProva(false);
            }
        }
    }

}
