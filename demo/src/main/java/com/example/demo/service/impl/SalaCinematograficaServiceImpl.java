package com.example.demo.service.impl;

import com.example.demo.exception.IdMustBeNullException;
import com.example.demo.exception.IdMustNotBeNullException;
import com.example.demo.model.SalaCinematografica;
import com.example.demo.repos.SalaCinematograficaRepository;
import com.example.demo.service.SalaCinematograficaService;

import java.util.List;

public class SalaCinematograficaServiceImpl implements SalaCinematograficaService {

  SalaCinematograficaRepository salaCinematograficaRepository;

  @Override
  public List<SalaCinematografica> findAll() {
    return salaCinematograficaRepository.findAll();
  }

  @Override
  public SalaCinematografica insert(SalaCinematografica salaCinematografica) {
    if(salaCinematografica.getId() != null && salaCinematografica.getId() > 0) {
      throw new IdMustBeNullException();
    }
    return salaCinematograficaRepository.save(salaCinematografica);
  }

  @Override
  public SalaCinematografica update(SalaCinematografica salaCinematografica) {
    if(salaCinematografica.getId() == null || salaCinematografica.getId() == 0) {
      throw new IdMustNotBeNullException();
    }
    return salaCinematograficaRepository.save(salaCinematografica);
  }

  @Override
  public Boolean deleteById(Long idSala) {
    salaCinematograficaRepository.deleteById(idSala);
    return salaCinematograficaRepository.findById(idSala).isEmpty();
  }

  @Override
  public SalaCinematografica findById(Long idSala) {
    return salaCinematograficaRepository.findById(idSala).orElse(SalaCinematografica.builder().build());
  }
}
