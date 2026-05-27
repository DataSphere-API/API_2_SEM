package org.datasphere.service;

import org.datasphere.dao.AulaDAO;
import org.datasphere.dao.interfaces.IDAO;
import org.datasphere.model.AulaModel;

import java.util.List;

public class AulaService {

    private IDAO<AulaModel> aulaDAO = new AulaDAO();

    public void salvarAulas(List<AulaModel> aulas) {
        for (AulaModel aula : aulas) {
            aulaDAO.salvar(aula);
        }
    }
}