package org.acme.domain;

import java.time.LocalDate;

public class Beneficiario extends Pessoa{
    public Beneficiario() {
    }

    public Beneficiario(String nome, String cpf, LocalDate dtNasc, String email, String telefone, Endereco endereco) {
        super(nome, cpf, dtNasc, email, telefone, endereco);
    }
}
