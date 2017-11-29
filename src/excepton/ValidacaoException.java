/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package excepton;

import java.util.ArrayList;
import java.util.List;

public class ValidacaoException extends RuntimeException {

    private final List<String> mensagens;
    public boolean validou;

    public ValidacaoException() {
        mensagens = new ArrayList<>();
        validou = true;
    }

    public ValidacaoException(String message) {
        super(message);
        mensagens = new ArrayList<>();
        validou = true;
    }

    public ValidacaoException(String message, Throwable e) {
        super(message, e);
        mensagens = new ArrayList<>();
        validou = true;
    }

    public void lancarException() {
        if (temMensagens()) {
            throw this;
        }
    }

    public void adicionarMensagem(String detail) {
        mensagens.add(detail);
        validou = false;
    }

    public boolean temMensagens() {
        return !mensagens.isEmpty();
    }

    public List<String> getMensagens() {
        return mensagens;
    }
}
