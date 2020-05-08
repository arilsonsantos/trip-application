package br.com.orion.buyprocess.exceptions.handler;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import br.com.orion.buyprocess.dto.RetornoDto;
import br.com.orion.buyprocess.exceptions.PagamentoException;
import feign.FeignException;

/**
 * ApiExceptionHandler
 */
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PagamentoException.class)
    public ResponseEntity<?> handlerPagamentoExcption(PagamentoException ex) {
        RetornoDto retorno = RetornoDto.builder().mensagem(ex.getMessage()).build();
        return new ResponseEntity<>(retorno, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.BadRequest.class)  
    public Map<String, Object> handleFeignStatusException(FeignException e, HttpServletResponse response) {
        response.setStatus(e.status());
        return new JSONObject(e.contentUTF8()).toMap(); 
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(fe -> errors.put(fe.getField(), fe.getDefaultMessage()));
        RetornoDto retorno = RetornoDto.builder().mensagem("Invalid arguement(s)").erros(errors).build();

        return new ResponseEntity<>(retorno, HttpStatus.BAD_REQUEST);
    }
}