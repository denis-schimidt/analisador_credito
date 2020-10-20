package com.financeiro.credito.analisadorcredito.controller.service;

import com.financeiro.credito.analisadorcredito.dto.Cliente;

import java.util.List;

public interface AnalisadorCredito {

    List<TipoCredito> obterCredito (Cliente cliente);

    boolean selecionadoPara(Cliente cliente);
}
