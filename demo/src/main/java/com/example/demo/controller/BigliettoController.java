package com.example.demo.controller;

import com.example.demo.dto.BigliettoDTO;
import com.example.demo.exception.IdMustBeNullException;
import com.example.demo.exception.IdMustNotBeNullException;
import com.example.demo.model.Biglietto;
import com.example.demo.service.BigliettoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/tickets")
@SecurityRequirement(name = "authentication")
public class BigliettoController {
  BigliettoService bigliettoService;

  @GetMapping("/v1")
  @Operation(description = """
      This method is used to retrieve all the tickets from the database<br>
      """)
  public List<BigliettoDTO> getAllTickets() {
    return bigliettoService.findAll().stream().map(Biglietto::toDto).toList();
  }

  @PostMapping("/v1")
  public BigliettoDTO saveTicket (@RequestBody BigliettoDTO bigliettoDTO) {
    try{
      Biglietto customer = bigliettoDTO.toModel();
      return bigliettoService.insert(customer).toDto();
    }
    catch(IdMustBeNullException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @PutMapping("/v1")
  public BigliettoDTO updateTicket (@RequestBody BigliettoDTO bigliettoDTO){
    try{
      Biglietto customer = bigliettoDTO.toModel();
      return bigliettoService.update(customer).toDto();
    }
    catch(IdMustNotBeNullException e) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, e.getMessage());
    }
  }

  @DeleteMapping("/v1/{id}")
  public Boolean deleteTicket(@PathVariable("id") Long idBiglietto) {
    return bigliettoService.deleteById(idBiglietto);
  }

  @GetMapping("/v1/{id}")
  public BigliettoDTO getTicketById(@PathVariable("id") Long idBiglietto) {
    return bigliettoService.findById(idBiglietto).toDto();
  }

}
