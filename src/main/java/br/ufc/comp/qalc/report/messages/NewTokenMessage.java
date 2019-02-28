package br.ufc.comp.qalc.report.messages;

import br.ufc.comp.qalc.frontend.token.Token;

/**
 * Mensagem específica para reportar o reconhecimento de um novo token na entrada.
 *
 * Esta mensagem se origina obrigatoriamente da Análise Léxica.
 */
public class NewTokenMessage extends Message {

    protected Token token;

    /**
     * Constrói uma mensagem informando o token que foi reconhecido.
     *
     * Informa à superclasse que a mensagem diz respeito à fase de Análise Léxica.
     *
     * @param newToken Token reconhecido.
     */
    public NewTokenMessage(Token newToken) {
        super(MessageType.SCANNING, null);

        this.token = newToken;
    }

    /**
     * Obtém o token reconhecido que essa mensagem carrega.
     *
     * @return Token reconhecido.
     */
    public Token getToken() {
        return token;
    }

}
