package br.com.fatec.bancodedados.blogango.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ResourceExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<StandardError> validacao (MethodArgumentNotValidException e, HttpServletRequest request){
    HttpStatus status = HttpStatus.BAD_REQUEST;

    ValidationError err = new ValidationError();
    err.setTimestamp(Instant.now());
    err.setStatus(status.value());
    err.setError("Erro de validação");
    err.setMessage("Verifique as validações dos campos");
    err.setPath(request.getRequestURI());

    for(FieldError f : e.getBindingResult().getFieldErrors()){
      err.addErro(f.getField(), f.getDefaultMessage());
    }

    return ResponseEntity.status(status).body(err);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<StandardError> entidadeNaoEcontrada(ResourceNotFoundException e, HttpServletRequest request){
    HttpStatus status = HttpStatus.NOT_FOUND;

    StandardError err = new StandardError();
    err.setTimestamp(Instant.now());
    err.setStatus(status.value());
    err.setError("Recurso não encontrado");
    err.setMessage(e.getMessage());
    err.setPath(request.getRequestURI());

    return ResponseEntity.status(status).body(err);
  }
}