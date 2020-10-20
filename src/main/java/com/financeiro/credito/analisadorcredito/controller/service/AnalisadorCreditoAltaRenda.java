package com.financeiro.credito.analisadorcredito.controller.service;

import com.financeiro.credito.analisadorcredito.dto.Cliente;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.financeiro.credito.analisadorcredito.controller.service.TipoCredito.*;

@Service
class AnalisadorCreditoAltaRenda implements AnalisadorCredito {
    private static final BigDecimal CINCO_MIL = new BigDecimal("5000");

    @Override
    public List<TipoCredito> obterCredito(Cliente cliente) {
        List<TipoCredito> tiposCredito = Lists.newArrayList(PESSOAL, CONSIGNADO);

        if(cliente.menosDe30Anos()) {
            tiposCredito.add(COM_GARANTIA);
        }

        return tiposCredito;
    }

    @Override
    public boolean selecionadoPara(Cliente cliente) {
        return cliente.isSalarioValidoPara(salario -> salario.compareTo(CINCO_MIL) >= 0);
    }
}
