package org.acme.domain;

public class Doacao {
    private Long id;
    private Double valor;
    private String descricao;

    public Doacao(){}

    public Doacao(Double valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}
