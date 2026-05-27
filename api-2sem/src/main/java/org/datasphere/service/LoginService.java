package org.datasphere.service;

import org.datasphere.dao.LoginDAO;
import org.datasphere.model.ProfessorModel;
import org.datasphere.model.SessaoUsuario;

public class LoginService {
    private LoginDAO loginDAO = new LoginDAO();

    public Boolean realizarLogin(String email, String senha){
        if (email == null || email.trim().isEmpty() || senha == null || senha.trim().isEmpty()) {
            return false;
        }
        ProfessorModel professor =loginDAO.checarLogin(email, senha);
        if (professor != null){
            SessaoUsuario.getSessao().setProfessorLogado(professor);
            return true;
        }
        return false;
    }

    public void realizarLogout(){
        SessaoUsuario.getSessao().limparSessao();
    }
}
