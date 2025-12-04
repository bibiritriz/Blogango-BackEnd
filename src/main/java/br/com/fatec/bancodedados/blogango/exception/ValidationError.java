package br.com.fatec.bancodedados.blogango.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends StandardError{
  private List<FieldMessage> erros = new ArrayList<>();

  public void addErro(String nomeCampo, String mensagem){
    this.erros.add(new FieldMessage(nomeCampo,mensagem));
  }
}
