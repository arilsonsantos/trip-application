package br.com.orion.bank.exceptions;

/**
 * PagamentoException
 */
public class PagamentoException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public PagamentoException(String message) {
        super(message);
    }
    
}