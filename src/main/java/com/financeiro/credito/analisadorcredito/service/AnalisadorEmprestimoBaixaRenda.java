package com.financeiro.credito.analisadorcredito.service;

import com.financeiro.credito.analisadorcredito.dto.Cliente;
import com.financeiro.credito.analisadorcredito.model.TipoEmprestimo;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

import static com.financeiro.credito.analisadorcredito.model.TipoEmprestimo.COM_GARANTIA;
import static com.financeiro.credito.analisadorcredito.model.TipoEmprestimo.PESSOAL;

@Service
class AnalisadorEmprestimoBaixaRenda implements AnalisadorEmprestimo {
    private static final BigDecimal TRES_MIL = new BigDecimal("3000");
    private static final Predicate<BigDecimal> MENOR_OU_IGUAL_A_3_000 = (salario) -> salario.compareTo(TRES_MIL) <= 0;

    @Override
    public List<TipoEmprestimo> listarTiposEmprestimosPara(Cliente cliente) {
        List<TipoEmprestimo> tiposCredito = Lists.newArrayList(PESSOAL);

        if (cliente.menosDe30AnosEResideEmSP()) {
            tiposCredito.add(COM_GARANTIA);
        }

        return tiposCredito;
    }

    @Override
    public boolean deveAnalisar(Cliente cliente) {
        return cliente.salarioEh(MENOR_OU_IGUAL_A_3_000);
    }
}
