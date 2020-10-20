package com.financeiro.credito.analisadorcredito.controller.service;

import java.math.BigDecimal;

public enum TipoCredito {
    PESSOAL(new BigDecimal("4.0")), COM_GARANTIA(new BigDecimal("3.0")), CONSIGNADO(new BigDecimal("2.0"));

    private BigDecimal taxaJuros;

    TipoCredito(BigDecimal taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public BigDecimal getTaxaJuros() {
        return taxaJuros;
    }
}
