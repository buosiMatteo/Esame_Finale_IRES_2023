package com.example.demo.exception;

public class FilmVietatoAiMinori extends Exception{
  public FilmVietatoAiMinori(){
    super("Lo spettatore non ha gli anni richiesti");
  }
}
