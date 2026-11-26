package com.example.dlqconsumer.infra.adapters.in.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

/**
 * DTO que representa o evento publicado no tópico SNS e encaminhado à fila.
 *
 * ATENÇÃO: ajuste os campos para ficarem IGUAIS ao JSON da mensagem original
 * usada no trabalho anterior (mesmos nomes e tipos).
 *
 * Exemplo de mensagem: {"pedidoId": 1, "valor": -10}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record EventoDto(
        Long pedidoId,
        BigDecimal valor
) {
}
