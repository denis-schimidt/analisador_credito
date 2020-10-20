package com.financeiro.credito.analisadorcredito.controller;

import com.financeiro.credito.analisadorcredito.controller.service.AnalisadorCredito;
import com.financeiro.credito.analisadorcredito.dto.Cliente;
import com.financeiro.credito.analisadorcredito.dto.CreditosPorClienteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class AnalisadorCreditoController {

    @Autowired
    private List<AnalisadorCredito> analisadoresCreditos;

    @PostMapping(value = "/creditos", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public CreditosPorClienteResponse obterCreditosPara(@RequestBody Cliente cliente) {
        return analisadoresCreditos
           .stream()
           .filter(analisadorCredito -> analisadorCredito.selecionadoPara(cliente))
           .findFirst()
           .map(analisadorCredito -> new CreditosPorClienteResponse(cliente.getNome(), analisadorCredito.obterCredito(cliente)))
           .orElseThrow(NenhumCreditoSelecionadoException::new);
    }
}
