package com.example.demo.service;

import com.example.demo.model.Film;
import com.example.demo.model.SalaCinematografica;

import java.util.List;

public interface SalaCinematograficaService {
  List<SalaCinematografica> findAll();

  SalaCinematografica insert(SalaCinematografica salaCinematografica);

  SalaCinematografica update(SalaCinematografica salaCinematografica);

  Boolean deleteById(Long idSala);

  SalaCinematografica findById(Long idSala);
}
