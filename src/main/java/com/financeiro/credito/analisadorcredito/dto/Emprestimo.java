package com.financeiro.credito.analisadorcredito.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financeiro.credito.analisadorcredito.model.TipoEmprestimo;

import java.math.BigDecimal;
import java.util.Objects;

public class Emprestimo {
    private final TipoEmprestimo tipoCredito;

    public Emprestimo(TipoEmprestimo tipoCredito) {
        this.tipoCredito = Objects.requireNonNull(tipoCredito);
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
