package com.financeiro.credito.analisadorcredito.dto;

import com.financeiro.credito.analisadorcredito.controller.service.TipoCredito;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

public class CreditosPorClienteResponse {
    private final String cliente;
    private final List<TipoCreditoDto> emprestimos;

    public CreditosPorClienteResponse(String cliente, List<TipoCredito> tiposCreditos) {
        this.cliente = cliente;
        this.emprestimos = tiposCreditos.stream().map(TipoCreditoDto::new).collect(toList());
    }

    public String getCliente() {
        return cliente;
    }

    public List<TipoCreditoDto> getEmprestimos() {
        return emprestimos;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, JSON_STYLE);
    }
}
