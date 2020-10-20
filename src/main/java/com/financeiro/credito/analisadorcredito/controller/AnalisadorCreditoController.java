package com.financeiro.credito.analisadorcredito.controller;

import com.financeiro.credito.analisadorcredito.dto.Cliente;
import com.financeiro.credito.analisadorcredito.dto.EmprestimosDisponiveisParaClienteResponse;
import com.financeiro.credito.analisadorcredito.service.AnalisadorEmprestimo;
import org.springframework.beans.factory.annotation.Autowired;
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
    private List<AnalisadorEmprestimo> analisadoresCreditos;

    @PostMapping(value = "/creditos", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public EmprestimosDisponiveisParaClienteResponse listarEmprestimosDisponiveisPara(@RequestBody Cliente cliente) {

        return analisadoresCreditos
           .stream()
           .filter(analisadorEmprestimo -> analisadorEmprestimo.selecionadoPara(cliente))
           .findFirst()
           .map(analisadorEmprestimo -> new EmprestimosDisponiveisParaClienteResponse(cliente.getNome(), analisadorEmprestimo.listarTiposEmprestimosPara(cliente)))
           .orElseThrow(NenhumCreditoSelecionadoException::new);
    }
}
