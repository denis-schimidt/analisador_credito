package com.financeiro.credito.analisadorcredito.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.financeiro.credito.analisadorcredito.dto.ErrosValidacao;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
class EndpointExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity handleValidationExceptions(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();

        List<String> erros = result.getFieldErrors()
                .stream()
                .map(error -> error.getField() + " -> " + error.getDefaultMessage())
                .collect(toList());

        return new ResponseEntity(new ErrosValidacao(erros), BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity handleValidationExceptions(HttpMessageNotReadableException e) throws IOException {
        String[] parteMensagemErro = e.getLocalizedMessage().split(";");

        return new ResponseEntity(new ErrosValidacao(List.of("Não foi possível desserializar o payload informado -> " + parteMensagemErro[0])), BAD_REQUEST);
    }
}
