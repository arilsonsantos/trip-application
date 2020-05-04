package br.com.orion.bank.exceptions.handler;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import br.com.orion.bank.exceptions.PagamentoException;
import br.com.orion.bank.model.dto.RetornoDto;

/**
 * ApiExceptionHandler
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PagamentoException.class)
    public ResponseEntity<?> handlerPagamentoExcption(PagamentoException ex) {
        RetornoDto retorno = RetornoDto.builder().mensagem(ex.getMessage()).build();
        return new ResponseEntity<>(retorno, HttpStatus.NOT_ACCEPTABLE);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fe -> erros.put(fe.getField(), fe.getDefaultMessage()));
        
        RetornoDto retorno = RetornoDto.builder().mensagem("Argumentos inválidos").erros(erros).build();
        return new ResponseEntity<>(retorno, HttpStatus.BAD_REQUEST);
    }

}
