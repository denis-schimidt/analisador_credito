package com.financeiro.credito.analisadorcredito.controller.service;

import com.financeiro.credito.analisadorcredito.dto.Cliente;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

import static com.financeiro.credito.analisadorcredito.controller.service.TipoCredito.COM_GARANTIA;
import static com.financeiro.credito.analisadorcredito.controller.service.TipoCredito.PESSOAL;

@Service
class AnalisadorCreditoMediaRenda implements AnalisadorCredito {
    private static final BigDecimal TRES_MIL = new BigDecimal("3000");
    private static final BigDecimal CINCO_MIL = new BigDecimal("5000");
    private static final Predicate<BigDecimal> MAIOR_QUE_3_000 = (salario) -> salario.compareTo(TRES_MIL) > 0;
    private static final Predicate<BigDecimal> MENOR_IGUAL_A_5_000 = (salario) -> salario.compareTo(CINCO_MIL) <= 0;

    @Override
    public List<TipoCredito> obterCredito(Cliente cliente) {
        List<TipoCredito> tiposCredito = Lists.newArrayList(PESSOAL);

        if (cliente.resideEmSP()) {
            tiposCredito.add(COM_GARANTIA);
        }

        return tiposCredito;
    }

    @Override
    public boolean selecionadoPara(Cliente cliente) {
        return cliente.isSalarioValidoPara(MAIOR_QUE_3_000.and(MENOR_IGUAL_A_5_000));
    }
}
