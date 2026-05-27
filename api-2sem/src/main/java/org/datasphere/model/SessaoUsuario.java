package org.datasphere.model;

public class SessaoUsuario {
    private static SessaoUsuario sessao;
    private ProfessorModel professorLogado;

    private SessaoUsuario(){}

    public static SessaoUsuario getSessao(){
        if (sessao == null){
            sessao = new SessaoUsuario();
        }
        return sessao;
    }

    public ProfessorModel getProfessorLogado(){
        return professorLogado;
    }

    public void setProfessorLogado(ProfessorModel professorLogado){
        this.professorLogado = professorLogado;
    }

    public void limparSessao(){
        this.professorLogado = null;
    }

    public Boolean estaLogado(){
        return this.professorLogado != null;
    }


}
