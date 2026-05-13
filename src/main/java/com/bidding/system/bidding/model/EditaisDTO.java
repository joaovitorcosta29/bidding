/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.bidding.model;

import java.util.Date;

/**
 *
 * @author Aluno
 */
public class EditaisDTO {
    private String titulo;
    private String descricao;
    private Date data_fechamento;
    private Boolean status;

    public EditaisDTO() {
    }

    public EditaisDTO(String titulo, String descricao, Date data_fechamento, Boolean status) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data_fechamento = data_fechamento;
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData_fechamento() {
        return data_fechamento;
    }

    public void setData_fechamento(Date data_fechamento) {
        this.data_fechamento = data_fechamento;
    }

    public Boolean isStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
}
