package org.acme.domain;

public class Endereco {

    private String cep;
    private String logradouro;
    private String bairro;
    private String estado;
    private String cidade;
    private String numero;
    private String complemento;

    public Endereco(){
        super();
    }

    public Endereco(String cep, String logradouro, String bairro, String estado, String cidade, String numero, String complemento) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.estado = estado;
        this.cidade = cidade;
        this.numero = numero;
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
