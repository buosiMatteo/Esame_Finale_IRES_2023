package com.example.demo.service.impl;

import com.example.demo.exception.IdMustBeNullException;
import com.example.demo.exception.IdMustNotBeNullException;
import com.example.demo.model.Film;
import com.example.demo.repos.FilmRepository;
import com.example.demo.service.FilmService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class FilmServiceImpl implements FilmService {

  FilmRepository filmRepository;

  @Override
  public List<Film> findAll() {
    return filmRepository.findAll();
  }

  @Override
  public Film insert(Film film) {
    if(film.getId() != null && film.getId() > 0) {
      throw new IdMustBeNullException();
    }
    return filmRepository.save(film);
  }

  @Override
  public Film update(Film film) {
    if(film.getId() == null || film.getId() == 0) {
      throw new IdMustNotBeNullException();
    }
    return filmRepository.save(film);
  }

  @Override
  public Boolean deleteById(Long idFilm) {
    filmRepository.deleteById(idFilm);
    return filmRepository.findById(idFilm).isEmpty();
  }

  @Override
  public Film findById(Long idFilm) {
    return filmRepository.findById(idFilm).orElse(Film.builder().build());
  }
}
