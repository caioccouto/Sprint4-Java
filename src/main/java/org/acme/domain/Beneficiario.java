package org.acme.domain;

public class Beneficiario extends Pessoa{
    public Beneficiario() {
    }

    public Beneficiario(String nome, int idade, String cpf, String email, String telefone, Endereco endereco) {
        super(nome, idade, cpf, email, telefone, endereco);
    }
}
