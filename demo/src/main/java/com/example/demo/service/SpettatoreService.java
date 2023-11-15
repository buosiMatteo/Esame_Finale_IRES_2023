package com.example.demo.service;

import com.example.demo.model.Film;
import com.example.demo.model.Spettatore;

import java.util.List;

public interface SpettatoreService {
  List<Spettatore> findAll();

  Spettatore insert(Spettatore spettatore);

  Spettatore update(Spettatore spettatore);

  Boolean deleteById(Long idSpettatore);

  Spettatore findById(Long idSpettatore);
}
