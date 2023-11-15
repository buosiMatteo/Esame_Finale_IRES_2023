package com.example.demo.service;

import com.example.demo.model.Cinema;

import java.util.List;

public interface CinemaService {
  List<Cinema> findAll();

  Cinema insert(Cinema cinema);

  Cinema update(Cinema cinema);

  Boolean deleteById(Long idCinema);

  Cinema findById(Long idCinema);
}
