/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bidding.system.bidding.service;

import com.bidding.system.bidding.model.LanceDTO;
import com.bidding.system.bidding.model.UserDTO;
import com.bidding.system.bidding.repository.LanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Aluno
 */
@Service
public class LanceService {
     
    @Autowired
    private LanceRepository repository;
    
    @Autowired
    private TokenService tokenService;
    
    public void criarLance(LanceDTO lance, String token){
        UserDTO userLogado = tokenService.extrairClaim(token);
        
        if(userLogado.getRole().equals("COMPRADOR")){
            String mensagem = "";
            if(lance.getValor() == null){
            mensagem += "Valor não preenchido!\n";
            }
            if(lance.getDataLance() == null){
            mensagem += "Data não preenchida!\n";
            }
            if(!mensagem.equals("")){
                throw new ResponseStatusException(HttpStatusCode.valueOf(400), mensagem);
            }
            
            int linhas = repository.criarLance(lance);
            if(linhas == 0){
                throw new ResponseStatusException(HttpStatusCode.valueOf(500), "Erro ao cadastrar no banco de dados");
            }
        }
            else{
                throw new ResponseStatusException(HttpStatusCode.valueOf(403), "Acesso não autorizado!");
            }
    }
}
