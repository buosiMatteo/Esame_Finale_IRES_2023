package com.example.demo.controller;

import com.example.demo.dto.SalaCinematograficaDTO;
import com.example.demo.exception.FilmVietatoAiMinori;
import com.example.demo.exception.IdMustBeNullException;
import com.example.demo.exception.IdMustNotBeNullException;
import com.example.demo.exception.SalaAlCompleto;
import com.example.demo.model.Biglietto;
import com.example.demo.model.Film;
import com.example.demo.model.SalaCinematografica;
import com.example.demo.model.Spettatore;
import com.example.demo.service.BigliettoService;
import com.example.demo.service.FilmService;
import com.example.demo.service.SalaCinematograficaService;
import com.example.demo.service.SpettatoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

import static com.example.demo.utility.DataConversionUtils.stringToLong;

@AllArgsConstructor
@RestController
@RequestMapping("/rooms")
@SecurityRequirement(name = "authentication")
public class SalaCinematograficaController {

  SalaCinematograficaService salaCinematograficaService;

  BigliettoService bigliettoService;

  SpettatoreService spettatoreService;

  @GetMapping("/v1")
  @Operation(description = """
      This method is used to retrieve all the rooms from the database<br>
      """)
  public List<SalaCinematograficaDTO> getAllRooms() {
    return salaCinematograficaService.findAll().stream().map(SalaCinematografica::toDto).toList();
  }

  @PostMapping("/v1")
  public SalaCinematograficaDTO saveRoom(@RequestBody SalaCinematograficaDTO salaCinematograficaDTO) {
    try {
      SalaCinematografica salaCinematografica = salaCinematograficaDTO.toModel();
      return salaCinematograficaService.insert(salaCinematografica).toDto();
    } catch (IdMustBeNullException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @PutMapping("/v1")
  public SalaCinematograficaDTO updateRoom(@RequestBody SalaCinematograficaDTO salaCinematograficaDTO) {
    try {
      SalaCinematografica salaCinematografica = salaCinematograficaDTO.toModel();
      return salaCinematograficaService.update(salaCinematografica).toDto();
    } catch (IdMustNotBeNullException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @DeleteMapping("/v1/{id}")
  public Boolean deleteRoom(@PathVariable("id") Long idSala) {
    return salaCinematograficaService.deleteById(idSala);
  }

  @GetMapping("/v1/{id}")
  public SalaCinematograficaDTO getRoomById(@PathVariable("id") Long idSala) {
    return salaCinematograficaService.findById(idSala).toDto();
  }

  @PutMapping("/v1/empty-room")
  public SalaCinematograficaDTO emptyRoom(@RequestBody SalaCinematograficaDTO salaCinematograficaDTO) {
    SalaCinematografica salaCinematografica = salaCinematograficaDTO.toModel();
    salaCinematografica.setNumeroSpettatori(0L);
    return salaCinematografica.toDto();
  }

  @PutMapping("/v1/add-person")
  @SneakyThrows
  public SalaCinematograficaDTO addPerson(@RequestBody SalaCinematograficaDTO salaCinematograficaDTO) {
    SalaCinematografica salaCinematografica = salaCinematograficaDTO.toModel();
    if (salaCinematografica.getNumeroSpettatori() < SalaCinematografica.NUMERO_MAX_SPETTATORI) {
      salaCinematografica.setNumeroSpettatori(salaCinematografica.getNumeroSpettatori() + 1L);
    } else {
      throw new SalaAlCompleto();
    }
    return salaCinematografica.toDto();
  }

  @GetMapping("/v1/incasso-sala")
  public Double incassoProiezione(@RequestParam("idSala") String idSala, @RequestParam("idBiglietto") String idBiglietto) {
    Biglietto biglietto = bigliettoService.findById(stringToLong(idBiglietto));
    SalaCinematografica salaCinematografica = salaCinematograficaService.findById(stringToLong(idSala));
    return biglietto.getPrezzo() * salaCinematografica.getNumeroSpettatori();

  }

  @PutMapping("/v1/eta-minima")
  @SneakyThrows
  public SalaCinematograficaDTO verificaEtaMinima(@RequestParam String idSala, @RequestParam String idSpettatore) {
    SalaCinematografica salaCinematografica = getRoomById(stringToLong(idSala)).toModel();
    Spettatore spettatore = spettatoreService.findById(stringToLong(idSpettatore));
    if (spettatore.isMaggiorenne(spettatore.getDataNascita())) {
      salaCinematografica.setNumeroSpettatori(salaCinematografica.getNumeroSpettatori() + 1);
    } else {
      throw new FilmVietatoAiMinori();
    }
    return salaCinematografica.toDto();
  }
}
