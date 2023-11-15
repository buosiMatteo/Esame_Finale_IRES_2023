package com.example.demo.controller;

import com.example.demo.dto.BigliettoDTO;
import com.example.demo.dto.CinemaDTO;
import com.example.demo.exception.IdMustBeNullException;
import com.example.demo.exception.IdMustNotBeNullException;
import com.example.demo.model.Biglietto;
import com.example.demo.model.Cinema;
import com.example.demo.service.BigliettoService;
import com.example.demo.service.CinemaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cinemas")
@SecurityRequirement(name = "authentication")
public class CinemaController {
  CinemaService cinemaService;

  @GetMapping("/v1")
  @Operation(description = """
      This method is used to retrieve all the cinemas from the database<br>
      """)
  public List<CinemaDTO> getAllCinemas() {
    return cinemaService.findAll().stream().map(Cinema::toDto).toList();
  }

  @PostMapping("/v1")
  public CinemaDTO saveCinema (@RequestBody CinemaDTO cinemaDTO) {
    try{
      Cinema cinema = cinemaDTO.toModel();
      return cinemaService.insert(cinema).toDto();
    }
    catch(IdMustBeNullException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @PutMapping("/v1")
  public CinemaDTO updateCinema (@RequestBody CinemaDTO cinemaDTO){
    try{
      Cinema cinema = cinemaDTO.toModel();
      return cinemaService.update(cinema).toDto();
    }
    catch(IdMustNotBeNullException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @DeleteMapping("/v1/{id}")
  public Boolean deleteCinema(@PathVariable("id") Long idCinema) {
    return cinemaService.deleteById(idCinema);
  }

  @GetMapping("/v1/{id}")
  public CinemaDTO getCinemaById(@PathVariable("id") Long idCInema) {
    return cinemaService.findById(idCInema).toDto();
  }
}
