package com.aly.notificacao.constants;

public enum EnumMensagens {
    PROPOSTA_MENSAGEM_GENERICA("Parabéns 🥳. Sua proposta foi recebida por nossa e será realizada uma análise. Até lá termine seu cadastro."),
    PRPOSTA_EM_ANALISE("Parabéns 🥳. Prezado(a) %s, sua proposta foi recebida por nossa equipe e será análisada. Em breve retornamos o contato.");

    private final String mensagem;

    EnumMensagens(final String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
