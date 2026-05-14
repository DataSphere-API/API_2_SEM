package org.datasphere.dao;

import org.datasphere.dao.interfaces.IDAO;
import org.datasphere.database.ConexaoDB;
import org.datasphere.model.MateriaModel;
import org.datasphere.model.TopicoModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MateriaDAO implements IDAO<MateriaModel> {


    @Override
    public void salvar(MateriaModel materiaModel) {
        String sql = "INSERT INTO materia (sigla, titulo, cargaHoraria) VALUES (?,?,?)";
        try(Connection conn = ConexaoDB.getConexao()){

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1,materiaModel.getSigla());
            ps.setString(2,materiaModel.getTitulo());
            ps.setInt(3,materiaModel.getCargaHoraria());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<MateriaModel> listar() {

        String sql = "SELECT * FROM materia";
        List<MateriaModel> materiaModelList = new ArrayList<>();
        try(Connection conn = ConexaoDB.getConexao()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                MateriaModel materiaModel = new MateriaModel();
                materiaModel.setSigla(rs.getString("sigla"));
                materiaModel.setTitulo(rs.getString("titulo"));
                materiaModel.setCargaHoraria(rs.getInt("carga_horaria"));

                materiaModelList.add(materiaModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materiaModelList;
    }

    @Override
    public void deletar(MateriaModel materia) {
        String sql = "DELETE FROM topico WHERE sigla = ?";
        try(Connection conn = ConexaoDB.getConexao()){

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, materia.getSigla());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
