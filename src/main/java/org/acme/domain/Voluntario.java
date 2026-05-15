package org.acme.domain;

import java.time.LocalDate;

public class Voluntario extends Pessoa{
    private String cro;
    private LocalDate dtCadastro;

    public Voluntario(String nome, String cpf, LocalDate dtNasc, String email, String tel, Endereco endereco, String cro, LocalDate dtCadastro) {
        super(nome, cpf, dtNasc, email, tel, endereco);
        this.cro = cro;
        this.dtCadastro = dtCadastro;
    }

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
    }

    public LocalDate getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(LocalDate dtCadastro) {
        this.dtCadastro = dtCadastro;
    }
}