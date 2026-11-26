package com.example.dlqconsumer.infra.adapters.in;

import com.example.dlqconsumer.infra.adapters.in.dto.EventoDto;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Adapter de entrada que escuta a DLQ.
 * Apenas registra no console o recebimento, o identificador do evento e seus dados.
 * (Sem reenvio automático e sem persistência em banco.)
 *
 * Se o método terminar sem exceção, a mensagem é removida da DLQ automaticamente.
 */
@Component
public class DlqListener {

    private static final Logger log = LoggerFactory.getLogger(DlqListener.class);

    @SqsListener("${app.sqs.dlq-name}")
    public void onMessage(EventoDto evento) {
        log.info("=== MENSAGEM RECEBIDA DA DLQ ===");
        log.info("Identificador do evento: {}", evento.pedidoId());
        log.info("Dados do evento: {}", evento);
    }
}
