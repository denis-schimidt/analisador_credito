package com.financeiro.credito.analisadorcredito.dto;

import com.financeiro.credito.analisadorcredito.model.TipoEmprestimo;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.builder.ToStringStyle.JSON_STYLE;

public class EmprestimosDisponiveisParaClienteResponse {
    private final String cliente;
    private final List<EmprestimoDto> emprestimos;

    public EmprestimosDisponiveisParaClienteResponse(String cliente, List<TipoEmprestimo> tiposCreditos) {
        this.cliente = cliente;
        this.emprestimos = tiposCreditos.stream().map(EmprestimoDto::new).collect(toList());
    }

    public String getCliente() {
        return cliente;
    }

    public List<EmprestimoDto> getEmprestimos() {
        return emprestimos;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, JSON_STYLE);
    }
}
