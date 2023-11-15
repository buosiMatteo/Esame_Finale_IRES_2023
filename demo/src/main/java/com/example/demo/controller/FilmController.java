package com.example.demo.controller;

import com.example.demo.dto.FilmDTO;
import com.example.demo.exception.IdMustBeNullException;
import com.example.demo.exception.IdMustNotBeNullException;
import com.example.demo.model.Film;
import com.example.demo.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/films")
@SecurityRequirement(name = "authentication")
public class FilmController {

  FilmService filmService;

  @GetMapping("/v1")
  @Operation(description = """
      This method is used to retrieve all the films from the database<br>
      """)
  public List<FilmDTO> getAllFilms() {
    return filmService.findAll().stream().map(Film::toDto).toList();
  }

  @PostMapping("/v1")
  public FilmDTO saveFilm (@RequestBody FilmDTO filmDTO) {
    try {
      Film film = filmDTO.toModel();
      return filmService.insert(film).toDto();
    } catch (IdMustBeNullException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @PutMapping("/v1")
  public FilmDTO updateFilm(@RequestBody FilmDTO filmDTO) {
    try {
      Film film = filmDTO.toModel();
      return filmService.update(film).toDto();
    } catch (IdMustNotBeNullException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @DeleteMapping("/v1/{id}")
  public Boolean deleteFilm(@PathVariable("id") Long idFilm) {
    return filmService.deleteById(idFilm);
  }

  @GetMapping("/v1/{id}")
  public FilmDTO getFilmById(@PathVariable("id") Long idFilm) {
    return filmService.findById(idFilm).toDto();
  }
}
