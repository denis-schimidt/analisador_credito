package com.financeiro.credito.analisadorcredito.controller.service;

import com.financeiro.credito.analisadorcredito.dto.Cliente;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.financeiro.credito.analisadorcredito.controller.service.TipoCredito.COM_GARANTIA;
import static com.financeiro.credito.analisadorcredito.controller.service.TipoCredito.PESSOAL;

@Service
class AnalisadorCreditoBaixaRenda implements AnalisadorCredito {
    private static final BigDecimal TRES_MIL = new BigDecimal("3000");

    @Override
    public List<TipoCredito> obterCredito(Cliente cliente) {
        List<TipoCredito> tiposCredito = Lists.newArrayList(PESSOAL);

        if (cliente.menosDe30AnosEResideEmSP()) {
            tiposCredito.add(COM_GARANTIA);
        }

        return tiposCredito;
    }

    @Override
    public boolean selecionadoPara(Cliente cliente) {
        return cliente.isSalarioValidoPara(salario -> salario.compareTo(TRES_MIL) <= 0);
    }
}
