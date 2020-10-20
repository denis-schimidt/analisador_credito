package com.financeiro.credito.analisadorcredito.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financeiro.credito.analisadorcredito.model.TipoEmprestimo;

import java.math.BigDecimal;

public class EmprestimoDto {
    private TipoEmprestimo tipoCredito;

    public EmprestimoDto(TipoEmprestimo tipoCredito) {
        this.tipoCredito = tipoCredito;
    }

    @JsonProperty
    public String tipo() {
        return tipoCredito.name().toLowerCase();
    }

    @JsonProperty
    public BigDecimal taxas() {
        return tipoCredito.getTaxaJuros();
    }
}
