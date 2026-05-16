package org.acme.domain;

import java.time.LocalDate;

public class Doador extends Pessoa{
    private String documento;

    public Doador(){}

    public Doador(String nome, String documento, LocalDate dtNasc, String email, String telefone, Endereco endereco) {
        super(nome, null, dtNasc, email, telefone, endereco);
        this.documento = documento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}
