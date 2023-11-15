package com.example.demo.service.impl;

import com.example.demo.exception.IdMustBeNullException;
import com.example.demo.exception.IdMustNotBeNullException;
import com.example.demo.model.Cinema;
import com.example.demo.repos.CinemaRepository;
import com.example.demo.service.CinemaService;

import java.util.List;

public class CinemaServiceImpl implements CinemaService {

  CinemaRepository cinemaRepository;

  @Override
  public List<Cinema> findAll() {
    return cinemaRepository.findAll();
  }

  @Override
  public Cinema insert(Cinema cinema) {
    if(cinema.getId() != null && cinema.getId() > 0) {
      throw new IdMustBeNullException();
    }
    return cinemaRepository.save(cinema);
  }

  @Override
  public Cinema update(Cinema cinema) {
    if(cinema.getId() == null || cinema.getId() == 0) {
      throw new IdMustNotBeNullException();
    }
    return cinemaRepository.save(cinema);
  }

  @Override
  public Boolean deleteById(Long idCinema) {
    cinemaRepository.deleteById(idCinema);
    return cinemaRepository.findById(idCinema).isEmpty();
  }

  @Override
  public Cinema findById(Long idCinema) {
    return cinemaRepository.findById(idCinema).orElse(Cinema.builder().build());
  }
}
