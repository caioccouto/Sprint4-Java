package org.acme.domain;

import java.time.LocalDate;

public class Dentista extends Pessoa{
    private String cro;

    public Dentista() {
    }

    public Dentista(String nome, String cpf, LocalDate dtNasc, String email, String telefone, Endereco endereco, String cro) {
        super(nome, cpf, dtNasc, email, telefone, endereco);
        this.cro = cro;
    }

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
    }
}
