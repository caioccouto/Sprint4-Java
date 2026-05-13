package org.acme.domain;

public class Pessoa {
    private Long id;
    private String nome;
    private Integer idade;
    private String cpf;
    private String email;
    private String telefone;
    private Endereco endereco;

    public Pessoa(){}

    public Pessoa(String nome, int idade, String cpf, String email, String telefone, Endereco endereco) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}
