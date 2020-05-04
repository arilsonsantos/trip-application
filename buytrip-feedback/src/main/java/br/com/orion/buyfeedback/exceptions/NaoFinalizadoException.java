package br.com.orion.buyfeedback.exceptions;

/**
 * NaoFinalizadoException
 */
public class NaoFinalizadoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NaoFinalizadoException(String message) {
        super(message);
    }

}