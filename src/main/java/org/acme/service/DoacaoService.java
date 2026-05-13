package org.acme.service;

import org.acme.domain.Doacao;

public class DoacaoService {

    public Doacao criar(Double valor, String descricao){
        return new Doacao(valor, descricao);
    }
}
