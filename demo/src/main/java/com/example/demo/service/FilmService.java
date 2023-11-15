package com.example.demo.service;

import com.example.demo.model.Biglietto;
import com.example.demo.model.Film;

import java.util.List;

public interface FilmService {
  List<Film> findAll();

  Film insert(Film film);

  Film update(Film film);

  Boolean deleteById(Long idFilm);

  Film findById(Long idFilm);
}
