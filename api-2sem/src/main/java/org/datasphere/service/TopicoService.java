package org.datasphere.service;

import org.datasphere.dao.TopicoDAO;
import org.datasphere.dao.interfaces.IDAO;
import org.datasphere.model.TopicoModel;

public class TopicoService {

    private IDAO<TopicoModel> topicoDAO = new TopicoDAO();

    public TopicoModel cadastrar(String titulo, Integer aulasNecessarias, Boolean prova) {
        TopicoModel novoTopico = new TopicoModel(titulo, aulasNecessarias, prova);
        topicoDAO.salvar(novoTopico);
        return novoTopico;
    }
}

