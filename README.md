# dlq-consumer

Aplicação Spring Boot que escuta a DLQ do SQS e registra no console o recebimento,
o identificador do evento e seus dados.

## Estrutura

```
src/main/java/com/example/dlqconsumer
├── DlqConsumerApplication.java
└── infra/adapters/in
    ├── DlqListener.java        # @SqsListener da DLQ
    └── dto/EventoDto.java      # formato da mensagem original
```

## Variáveis de ambiente

| Variável                | Obrigatória | Descrição                                         |
|-------------------------|-------------|---------------------------------------------------|
| AWS_ACCESS_KEY_ID       | sim         | Access key                                        |
| AWS_SECRET_ACCESS_KEY   | sim         | Secret key                                        |
| AWS_SESSION_TOKEN       | se houver   | Necessária em contas temporárias (ex.: AWS Academy) |
| AWS_REGION              | não         | Padrão `us-east-1`                                |
| SQS_DLQ_NAME            | não         | Nome da DLQ. Padrão `pedidos-dlq`                 |

### Linux / macOS
```bash
export AWS_ACCESS_KEY_ID=...
export AWS_SECRET_ACCESS_KEY=...
export AWS_SESSION_TOKEN=...
export AWS_REGION=us-east-1
export SQS_DLQ_NAME=pedidos-dlq
./mvnw spring-boot:run   # ou: mvn spring-boot:run
```

### Windows (PowerShell)
```powershell
$env:AWS_ACCESS_KEY_ID="..."
$env:AWS_SECRET_ACCESS_KEY="..."
$env:AWS_SESSION_TOKEN="..."
$env:AWS_REGION="us-east-1"
$env:SQS_DLQ_NAME="pedidos-dlq"
mvn spring-boot:run
```

## Teste
1. Suba esta aplicação.
2. Publique no tópico SNS uma mensagem que faça o consumer principal falhar,
   por exemplo `{"pedidoId":1,"valor":-10}`.
3. Após o limite de recebimentos da fila principal, a mensagem vai para a DLQ
   e aparece no console desta aplicação.

## Observações
- Ajuste `EventoDto` para ficar idêntico ao JSON da mensagem original.
- A fila principal precisa ter `RedrivePolicy` apontando para a DLQ.
- Se a fila for FIFO, o nome da DLQ termina em `.fifo`.
