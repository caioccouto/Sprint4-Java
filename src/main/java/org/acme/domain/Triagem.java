package org.acme.domain;

import java.time.LocalDate;

public class Triagem {
    private Long id;
    private Long idBenef;
    private Long idVolun;
    private LocalDate dtInicio;
    private LocalDate dtFim;
    private ResultadoTriagem resultado;

    public Triagem(){}

    public Triagem(Long idBenef, Long idVolun, LocalDate dtInicio, LocalDate dtFim, ResultadoTriagem resultado) {
        this.idBenef = idBenef;
        this.idVolun = idVolun;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
        this.resultado = resultado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdBenef() {
        return idBenef;
    }

    public void setIdBenef(Long idBenef) {
        this.idBenef = idBenef;
    }

    public Long getIdVolun() {
        return idVolun;
    }

    public void setIdVolun(Long idVolun) {
        this.idVolun = idVolun;
    }

    public LocalDate getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(LocalDate dtInicio) {
        this.dtInicio = dtInicio;
    }

    public LocalDate getDtFim() {
        return dtFim;
    }

    public void setDtFim(LocalDate dtFim) {
        this.dtFim = dtFim;
    }

    public ResultadoTriagem getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoTriagem resultado) {
        this.resultado = resultado;
    }
}
