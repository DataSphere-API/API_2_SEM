package org.datasphere.service;

import org.datasphere.model.ProfessorModel;

public class SessaoUsuarioService {

        private static ProfessorModel professorLogado;

        public static ProfessorModel getProfessorLogado() {
            return professorLogado;
        }

        public static void setProfessorLogado(ProfessorModel professor) {
            professorLogado = professor;
        }

        //logout
        public static void limparSessao() {
            professorLogado = null;
        }
    }
