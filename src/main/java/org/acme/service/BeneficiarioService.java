package org.acme.service;

import org.acme.domain.Beneficiario;
import org.acme.domain.Endereco;

public class BeneficiarioService {

    public Beneficiario criar(String nome, Integer idade, String cpf, String email, String telefone, Endereco endereco){
        return new Beneficiario(nome, idade, cpf, email, telefone, endereco);
    }
}
