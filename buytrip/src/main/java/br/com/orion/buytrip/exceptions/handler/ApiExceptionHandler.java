package br.com.orion.buytrip.exceptions.handler;

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
import br.com.orion.buytrip.dto.RetornoDto;
import br.com.orion.buytrip.exceptions.PagamentoException;

/** ApiExceptionHandler */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PagamentoException.class)
    public ResponseEntity<?> handlerPagamentoExcption(PagamentoException ex) {
        RetornoDto retorno = RetornoDto.builder().mensagem(ex.getMessage()).build();
        return new ResponseEntity<>(retorno, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(fe -> errors.put(fe.getField(), fe.getDefaultMessage()));

        RetornoDto retorno =
                RetornoDto.builder().mensagem("Invalid arguement(s)").errors(errors).build();

        return new ResponseEntity<>(retorno, HttpStatus.BAD_REQUEST);
    }
}
