package br.com.fatec.bancodedados.blogango.exception;

public class ResourceNotFoundException extends RuntimeException{
  public ResourceNotFoundException(String mensagem){
    super(mensagem);
  }
}
