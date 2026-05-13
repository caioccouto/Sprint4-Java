package org.acme.service;

import org.acme.domain.Dentista;
import org.acme.domain.Endereco;

public class DentistaService {

    public Dentista criar(String nome, Integer idade, String cpf, String email, String telefone, Endereco endereco, String cro){
        return new Dentista(nome, idade, cpf, email, telefone, endereco, cro);
    }
}
