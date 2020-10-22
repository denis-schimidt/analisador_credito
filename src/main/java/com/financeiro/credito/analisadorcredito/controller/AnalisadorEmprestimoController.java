package com.financeiro.credito.analisadorcredito.controller;

import com.financeiro.credito.analisadorcredito.dto.Cliente;
import com.financeiro.credito.analisadorcredito.dto.EmprestimosDisponiveisParaCliente;
import com.financeiro.credito.analisadorcredito.service.AnalisadorEmprestimo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/v1/", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class AnalisadorEmprestimoController {

    @Autowired
    private List<AnalisadorEmprestimo> analisadoresEmprestimo;

    @PostMapping(value = "/emprestimos")
    @ResponseStatus(OK)
    public EmprestimosDisponiveisParaCliente listarEmprestimosDisponiveisPara(@Valid @RequestBody Cliente cliente) {

        return analisadoresEmprestimo
           .stream()
           .filter(analisadorEmprestimo -> analisadorEmprestimo.selecionadoPara(cliente))
           .findFirst()
           .map(analisadorEmprestimo -> new EmprestimosDisponiveisParaCliente(cliente.getNome(), analisadorEmprestimo.listarTiposEmprestimosPara(cliente)))
           .orElseThrow(NenhumCreditoSelecionadoException::new);
    }
}
