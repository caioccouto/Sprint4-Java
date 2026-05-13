package org.acme.domain;

public class Dentista extends Pessoa{
    private String cro;

    public Dentista() {
    }

    public Dentista(String nome, int idade, String cpf, String email, String telefone, Endereco endereco, String cro) {
        super(nome, idade, cpf, email, telefone, endereco);
        this.cro = cro;
    }

    public String getCro() {
        return cro;
    }
}
