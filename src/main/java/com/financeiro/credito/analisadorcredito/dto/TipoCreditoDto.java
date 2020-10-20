package com.financeiro.credito.analisadorcredito.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financeiro.credito.analisadorcredito.controller.service.TipoCredito;

import java.math.BigDecimal;

public class TipoCreditoDto {
    private TipoCredito tipoCredito;

    public TipoCreditoDto(TipoCredito tipoCredito) {
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
