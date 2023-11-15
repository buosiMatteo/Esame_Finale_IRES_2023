package com.example.demo.controller;

import com.example.demo.dto.SpettatoreDTO;
import com.example.demo.exception.IdMustBeNullException;
import com.example.demo.exception.IdMustNotBeNullException;
import com.example.demo.model.Spettatore;
import com.example.demo.service.SpettatoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/watchers")
@SecurityRequirement(name = "authentication")
public class SpettatoreController {

  SpettatoreService spettatoreService;

  @GetMapping("/v1")
  @Operation(description = """
      This method is used to retrieve all the watchers from the database<br>
      """)
  public List<SpettatoreDTO> getAllWatcher() {
    return spettatoreService.findAll().stream().map(Spettatore::toDto).toList();
  }

  @PostMapping("/v1")
  public SpettatoreDTO saveWatcher(@RequestBody SpettatoreDTO spettatoreDTO) {
    try{
      Spettatore spettatore = spettatoreDTO.toModel();
      return spettatoreService.insert(spettatore).toDto();
    }
    catch(IdMustBeNullException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @PutMapping("/v1")
  public SpettatoreDTO updateWatcher(@RequestBody SpettatoreDTO spettatoreDTO){
    try{
      Spettatore spettatore = spettatoreDTO.toModel();
      return spettatoreService.update(spettatore).toDto();
    }
    catch(IdMustNotBeNullException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @DeleteMapping("/v1/{id}")
  public Boolean deleteWatcher(@PathVariable("id") Long idSpettatore) {
    return spettatoreService.deleteById(idSpettatore);
  }

  @GetMapping("/v1/{id}")
  public SpettatoreDTO getWatcherById(@PathVariable("id") Long idSpettatore) {
    return spettatoreService.findById(idSpettatore).toDto();
  }

  @GetMapping("/v1/maggiorenne")
  public Boolean isMaggiorenne(@RequestParam Long idSpettatore){
    Spettatore spettatore = getWatcherById(idSpettatore).toModel();
    return spettatore.isMaggiorenne(spettatore.getDataNascita());
  }


  @GetMapping("/v1/eta-spettatore")
  public Integer etaSpettatore(@RequestParam Long idSpettatore){
    Spettatore spettatore = getWatcherById(idSpettatore).toModel();
    return spettatore.etaSpettatore(spettatore.getDataNascita());
  }
}
