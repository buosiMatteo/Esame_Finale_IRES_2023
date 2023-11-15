package com.example.demo.service.impl;

import com.example.demo.exception.IdMustBeNullException;
import com.example.demo.exception.IdMustNotBeNullException;
import com.example.demo.model.Spettatore;
import com.example.demo.repos.SpettatoreRepository;
import com.example.demo.service.SpettatoreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class SpettatoreServiceImpl implements SpettatoreService {

  SpettatoreRepository spettatoreRepository;

  @Override
  public List<Spettatore> findAll() {
    return spettatoreRepository.findAll();
  }

  @Override
  public Spettatore insert(Spettatore spettatore) {
    if(spettatore.getId() != null && spettatore.getId() > 0) {
      throw new IdMustBeNullException();
    }
    return spettatoreRepository.save(spettatore);
  }

  @Override
  public Spettatore update(Spettatore spettatore) {
    if(spettatore.getId() == null || spettatore.getId() == 0) {
      throw new IdMustNotBeNullException();
    }
    return spettatoreRepository.save(spettatore);
  }

  @Override
  public Boolean deleteById(Long idSpettatore) {
    spettatoreRepository.deleteById(idSpettatore);
    return spettatoreRepository.findById(idSpettatore).isEmpty();
  }

  @Override
  public Spettatore findById(Long idSpettatore) {
    return spettatoreRepository.findById(idSpettatore).orElse(Spettatore.builder().build());
  }
}
