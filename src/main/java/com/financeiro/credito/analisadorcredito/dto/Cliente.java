package com.financeiro.credito.analisadorcredito.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.function.Predicate;

import static java.util.Optional.ofNullable;

public class Cliente {
    private final String nome;
    private final String cpf;
    private final int idade;
    private final String estado;
    private final BigDecimal salario;

    @JsonCreator
    Cliente(@JsonProperty("nome") String nome, @JsonProperty("cpf") String cpf, @JsonProperty("idade") int idade, @JsonProperty("estado") String estado, @JsonProperty("salario") BigDecimal salario) {
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

    public String getEstado() {
        return estado;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public boolean isSalario(Predicate<BigDecimal> condicoes) {
        return condicoes.test(salario);
    }

    public boolean menosDe30Anos() {
        return idade < 30;
    }

    public boolean resideEmSP() {
        return ofNullable(estado).map(estado -> estado.equalsIgnoreCase("SP")).orElse(false);
    }

    public boolean menosDe30AnosEResideEmSP() {
        return menosDe30Anos() && resideEmSP();
    }
}
