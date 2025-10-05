package com.aly.notificacao.listener;

import com.aly.notificacao.constants.EnumMensagens;
import com.aly.notificacao.domain.Proposta;
import com.aly.notificacao.domain.Usuario;
import com.aly.notificacao.service.NotificacaoSnsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class PropostaPendenteListener {
    private static String MENSAGEM_NOTIFICACAO_SNS = EnumMensagens.PROPOSTA_MENSAGEM_GENERICA.getMensagem();

    private final NotificacaoSnsService notificacaoSnsService;

    @RabbitListener(queues = "${rabbit.mq.queue.pp.ms.notificacao}")
    public void consumerFilaPropostaPendente(Proposta proposta) {
        log.info("[RabbitMQ] - Consumindo mensagem da fila: {}", proposta);

        Usuario usuarioProposta = proposta.getUsuario() != null ? proposta.getUsuario() : null;

        String telefone = null;

        if (usuarioProposta != null && usuarioProposta.getTelefone() != null) {
            MENSAGEM_NOTIFICACAO_SNS = String.format(EnumMensagens.PRPOSTA_EM_ANALISE.getMensagem(), proposta.getUsuario().getNome());
            telefone = usuarioProposta.getTelefone();
        }

        notificacaoSnsService.notificarViaSMS(MENSAGEM_NOTIFICACAO_SNS, telefone);
    }
}
