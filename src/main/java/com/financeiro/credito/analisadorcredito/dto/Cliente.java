package com.financeiro.credito.analisadorcredito.dto;

import br.com.caelum.stella.type.Estado;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.function.Predicate;

import static br.com.caelum.stella.type.Estado.SP;
import static java.util.Optional.ofNullable;

public class Cliente {

    @NotBlank @Size(min = 4, max = 60)
    private final String nome;

    @NotNull @CPF
    private final String cpf;

    @Min(18) @Max(130)
    private final int idade;

    @NotNull
    private final Estado estado;

    @NotNull @DecimalMin("1.0")
    private final BigDecimal salario;

    @JsonCreator
    Cliente(String nome, String cpf, int idade, Estado estado, BigDecimal salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.estado = estado;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public int getIdade() {
        return idade;
    }

    public Estado getEstado() {
        return estado;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public boolean salarioEh(Predicate<BigDecimal> condicoes) {
        return condicoes.test(salario);
    }

    public boolean menosDe30Anos() {
        return idade < 30;
    }

    public boolean resideEmSP() {
        return ofNullable(estado).map(estado -> estado == SP).orElse(false);
    }

    public boolean menosDe30AnosEResideEmSP() {
        return menosDe30Anos() && resideEmSP();
    }
}
