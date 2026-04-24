package org.datasphere.dao;

import org.datasphere.dao.interfaces.IDAO;
import org.datasphere.database.ConexaoDB;
import org.datasphere.model.TopicoModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopicoDAO implements IDAO <TopicoModel> {

    @Override
    public void salvar(TopicoModel topico){
        String sql = "INSERT INTO topico (titulo, aulas_necessarias) VALUES (?,?)";
        try(Connection conn = ConexaoDB.getConexao()){

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1,topico.getTitulo());
            ps.setInt(2,topico.getAulasNecessarias());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                topico.setId(rs.getLong(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TopicoModel> listar(){
        String sql = "SELECT * FROM topico ORDER BY ID";
        List<TopicoModel> topicoModelList = new ArrayList<>();
        try(Connection conn = ConexaoDB.getConexao()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                TopicoModel topicoModel = new TopicoModel();
                topicoModel.setId(rs.getLong("id"));
                topicoModel.setTitulo(rs.getString("titulo"));
                topicoModel.setAulasNecessarias(rs.getInt("aulas_necessarias"));

                topicoModelList.add(topicoModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return topicoModelList;
    }

    @Override
    public void deletar(TopicoModel topico){
        String sql = "DELETE FROM topico WHERE id = ?";
        try(Connection conn = ConexaoDB.getConexao()){

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, topico.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
