package org.datasphere.service;

import org.datasphere.dao.TopicoDAO;
import org.datasphere.dao.interfaces.IDAO;
import org.datasphere.model.TopicoModel;

public class TopicoService {

    private IDAO<TopicoModel> topicoDAO = new TopicoDAO();

    public TopicoModel cadastrar(String titulo, int aulasMinimas, int aulasMaximas, boolean prova) {

        if (aulasMinimas > aulasMaximas) {
            throw new IllegalArgumentException("O número mínimo de aulas não pode ser maior que o máximo.");
        }

        TopicoModel novoTopico = new TopicoModel(titulo, aulasMinimas, aulasMaximas, prova);

        topicoDAO.salvar(novoTopico);
        return novoTopico;
    }
}