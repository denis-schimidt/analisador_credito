package com.financeiro.credito.analisadorcredito.service;

import com.financeiro.credito.analisadorcredito.dto.Cliente;
import com.financeiro.credito.analisadorcredito.model.TipoEmprestimo;

import java.util.List;

public interface AnalisadorEmprestimo {

    List<TipoEmprestimo> listarTiposEmprestimosPara(Cliente cliente);

    boolean deveAnalisar(Cliente cliente);
}
