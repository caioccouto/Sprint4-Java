package org.acme.domain;

public class Doacao {
    private Long id;
    private Double valor;
    private String descricao;
    private Long doadorId;

    public Doacao(){}

    public Doacao(Double valor, String descricao, Long doadorId) {
        this.valor = valor;
        this.descricao = descricao;
        this.doadorId = doadorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getDoadorId() {
        return doadorId;
    }

    public void setDoadorId(Long doadorId) {
        this.doadorId = doadorId;
    }
}
