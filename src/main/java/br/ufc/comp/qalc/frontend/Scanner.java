package br.ufc.comp.qalc.frontend;

import br.ufc.comp.qalc.frontend.token.*;

import java.io.IOException;

/**
 * Analisador Léxico da linguagem.
 * <p>
 * Funciona como uma fonte de tokens, de onde o Analisador Sintático
 * deverá ler.
 *
 * @see Source
 */
public class Scanner {

    /**
     * Último token reconhecido.
     */
    protected Token currentToken;
    /**
     * Fonte de caracteres de onde extrair os tokens.
     */
    protected Source source;

    /**
     * Constrói um Analisador Léxico a partir de uma fonte de caracteres.
     *
     * @param sourceStream Fonte a ser utilizada.
     */
    public Scanner(Source sourceStream) {
        // FIXME Lidar corretamente com as exceções.
        this.source = sourceStream;
    }

    /**
     * Consome caracteres da fonte até que eles componham um lexema de
     * um dos tokens da linguagem, coinstrói um objeto representando esse
     * token associado ao lexema lido e o retorna.
     *
     * @return Token reconhecido.
     * @throws IOException Caso haja problema na leitura da fonte.
     */
    public Token getNextToken() throws IOException {
        // TODO Reconhecimento de tokens

        if (source.getCurrentChar() == Source.EOF) {
            return new EOFToken(source.getCurrentLine(), source.getCurrentColumn());
        } else if (Character.isDigit(source.getCurrentChar())) { // NumberToken
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            do {
                lexema.append(source.getCurrentChar());
                source.advance();
            } while (Character.isDigit(source.getCurrentChar()));

            String stringValue = lexema.toString();

            return new NumberToken(currentLine, lexemeStart, stringValue);
        }

        else if (source.getCurrentChar() == ('@')) { // FunctionIdentifierToken
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            do {
                lexema.append(source.getCurrentChar());
                source.advance();
            } while (Character.isLetterOrDigit(source.getCurrentChar()));

            String stringValue = lexema.toString();

            return new FunctionIdentifierToken(currentLine, lexemeStart, stringValue);
        }

        else if (source.getCurrentChar() == ('$')) {  // VariableIdentifierToken
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            do {
                lexema.append(source.getCurrentChar());
                source.advance();
            } while (Character.isLetter(source.getCurrentChar()));

            String stringValue = lexema.toString();

            return new VariableIdentifierToken(currentLine, lexemeStart, stringValue);
        }

        else if ((source.getCurrentChar() == ('$')) || (source.getCurrentChar() == ('?')) ) {  //  ResultIdentifierToken
            StringBuilder lexema = new StringBuilder();

            int isZero = 0;
            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            do {
                if(source.getCurrentChar() == 0) {
                    isZero = isZero + 1;
                }
                else {
                    isZero = 0;
                }
                lexema.append(source.getCurrentChar());
                source.advance();
            } while (Character.isDigit(source.getCurrentChar()) &&  (isZero < 2));

            String stringValue = lexema.toString();

            return new ResultIdentifierToken(currentLine, lexemeStart, stringValue);
        }

        else if (source.getCurrentChar() == ('=')) {  //AtribToken
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();

            lexema.append(source.getCurrentChar());
            source.advance();

            String stringValue = lexema.toString();

            return new AtribToken(currentLine, lexemeStart, stringValue);

        }

        else if (source.getCurrentChar() == ('+')) { //PlusToken
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();
            lexema.append(source.getCurrentChar());
            source.advance();

            String stringValue = lexema.toString();

            return new  PlusToken(currentLine, lexemeStart, stringValue);

        }

        else if (source.getCurrentChar() == ('-')) { //MinusToken
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();
            lexema.append(source.getCurrentChar());
            source.advance();

            String stringValue = lexema.toString();

            return new  MinusToken(currentLine, lexemeStart, stringValue);

        }

        else if (source.getCurrentChar() == ('*')) { //TimesToken
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();
            lexema.append(source.getCurrentChar());
            source.advance();

            String stringValue = lexema.toString();

            return new  TimesToken(currentLine, lexemeStart, stringValue);

        }

        else if (source.getCurrentChar() == ('(')) {  // LparenToken
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();
            lexema.append(source.getCurrentChar());
            source.advance();

            String stringValue = lexema.toString();

            return new LparenToken(currentLine, lexemeStart, stringValue);

        }

        else if (source.getCurrentChar() == (')')) { //  LparenToken
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();
            lexema.append(source.getCurrentChar());
            source.advance();

            String stringValue = lexema.toString();

            return new RparenToken(currentLine, lexemeStart, stringValue);

        }

        else if (source.getCurrentChar() == (',')) { //  CommaToken
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();
            lexema.append(source.getCurrentChar());
            source.advance();

            String stringValue = lexema.toString();

            return new CommaToken(currentLine, lexemeStart, stringValue);

        }

        else if (source.getCurrentChar() == (';')) { //  SemiToken
            StringBuilder lexema = new StringBuilder();

            long currentLine = source.getCurrentLine();
            long lexemeStart = source.getCurrentColumn();
            lexema.append(source.getCurrentChar());
            source.advance();

            String stringValue = lexema.toString();

            return new SemiToken(currentLine, lexemeStart, stringValue);

        }

        else {
            return null;
        }

        // TODO Recuperação de erros.
    }

    /**
     * Obtém o último token reconhecido.
     *
     * @return O último token reconhecido.
     */
    public Token getCurrentToken() {
        return currentToken;
    }
}
