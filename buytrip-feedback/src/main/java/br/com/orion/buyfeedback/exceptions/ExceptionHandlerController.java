package br.com.orion.buyfeedback.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ExceptionHandlerController
 */
@ControllerAdvice
public class ExceptionHandlerController   {

    @ExceptionHandler(NaoFinalizadoException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String process(NaoFinalizadoException ex) {
        return "Compra ainda não finalizada.";
    }
}