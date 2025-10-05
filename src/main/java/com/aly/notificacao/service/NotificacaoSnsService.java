package com.aly.notificacao.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class NotificacaoSnsService {
    private final AmazonSNS amazonSNS;

    public void notificarViaSMS(String mensagem, String telefone) {
       if (telefone == null || telefone.isBlank()) {
           log.error("[SNS] - Não foi possível mandar para o número de telefone: {}", telefone);
       }

        PublishRequest requestParaSerEnviadaAoSNS = new PublishRequest()
                .withMessage(mensagem)
                .withPhoneNumber(telefone);

       log.info("[SNS] - Enviando mensagem para o telefone: {}", telefone);
       amazonSNS.publish(requestParaSerEnviadaAoSNS);
    }
}
