package org.datasphere.service;

import org.datasphere.dao.DiaDAO;
import org.datasphere.dao.interfaces.IDAO;
import org.datasphere.model.*;
import org.datasphere.dao.AulaPlanejadaDAO;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class SemestreService {

    private SemestreModel semestre;

    public SemestreModel getSemestre() { return semestre; }
    public void setSemestre(SemestreModel semestre) { this.semestre = semestre; }

    private DiaDAO diaDAO = new DiaDAO();

    public void criarSemestreComDias() {
        for (LocalDate dia = semestre.getDiaInicio(); !dia.isAfter(semestre.getDiaFim()); dia = dia.plusDays(1)) {
            if (!dia.getDayOfWeek().equals(DayOfWeek.SATURDAY) && !dia.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                DiaModel novoDia = new DiaModel(dia, true);
                semestre.adicionarDias(novoDia);
                diaDAO.salvar(novoDia);
            }
        }
    }

    public void criarSemanaSprint(LocalDate dataInicial, LocalDate dataFinal) {
        for (DiaModel dia : semestre.getDiasList()) {
            LocalDate dataAtual = dia.getData();
            if (!dataAtual.isBefore(dataInicial) && !dataAtual.isAfter(dataFinal)) {
                dia.setDisponivelParaProva(false);
                diaDAO.atualizarDisponibilidade(dia.getData());
            }
        }
    }

}
