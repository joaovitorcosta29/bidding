/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.bidding.repository;

import com.bidding.system.bidding.model.EditaisDTO;
import com.bidding.system.bidding.model.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aluno
 */
@Repository
public class EditaisRepository {
    public void criar(EditaisDTO editais){

        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;

            stmt = conn.prepareStatement("INSERT INTO editais (titulo, descricao, data_fechamento, status) VALUES (?,?,?,?)");

            stmt.setString(1, editais.getTitulo());
            stmt.setString(2, editais.getDescricao());
            stmt.setDate(3, editais.getData_fechamento());
            stmt.setBoolean(4, editais.getStatus());

            int linhasAfetadas = stmt.executeUpdate();
            if(linhasAfetadas == 0){
                throw new SQLException("Falha na criação - Nenhuma linha foi afetada");
            }
            

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }
}
