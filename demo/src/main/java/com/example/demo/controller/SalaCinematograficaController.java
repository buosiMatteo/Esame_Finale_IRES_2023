package com.example.demo.controller;

import com.example.demo.dto.SalaCinematograficaDTO;
import com.example.demo.exception.IdMustBeNullException;
import com.example.demo.exception.IdMustNotBeNullException;
import com.example.demo.model.SalaCinematografica;
import com.example.demo.service.SalaCinematograficaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/rooms")
@SecurityRequirement(name = "authentication")
public class SalaCinematograficaController {

  SalaCinematograficaService salaCinematograficaService;

  @GetMapping("/v1")
  @Operation(description = """
      This method is used to retrieve all the rooms from the database<br>
      """)
  public List<SalaCinematograficaDTO> getAllRooms() {
    return salaCinematograficaService.findAll().stream().map(SalaCinematografica::toDto).toList();
  }

  @PostMapping("/v1")
  public SalaCinematograficaDTO saveRoom(@RequestBody SalaCinematograficaDTO salaCinematograficaDTO) {
    try{
      SalaCinematografica salaCinematografica = salaCinematograficaDTO.toModel();
      return salaCinematograficaService.insert(salaCinematografica).toDto();
    }
    catch(IdMustBeNullException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @PutMapping("/v1")
  public SalaCinematograficaDTO updateRoom(@RequestBody SalaCinematograficaDTO salaCinematograficaDTO){
    try{
      SalaCinematografica salaCinematografica = salaCinematograficaDTO.toModel();
      return salaCinematograficaService.update(salaCinematografica).toDto();
    }
    catch(IdMustNotBeNullException e) {
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
}
