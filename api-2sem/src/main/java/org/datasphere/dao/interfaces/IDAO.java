package org.datasphere.dao.interfaces;

import java.util.List;

public interface IDAO<T> {

    public void salvar(T obj);
    public List<T> listar();
    public void deletar(Long id);

}
