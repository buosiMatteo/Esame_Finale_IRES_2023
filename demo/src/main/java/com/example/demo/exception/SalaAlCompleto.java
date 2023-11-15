package com.example.demo.exception;

public class SalaAlCompleto extends Exception {

  public SalaAlCompleto (){
    super("La sala è al completo");
  }

  public SalaAlCompleto(String message) {
    super(message);
  }
}
