package com.example.demo.dto;

import com.example.demo.dto.archetype.Dto;
import com.example.demo.dto.archetype.Model;
import com.example.demo.model.Spettatore;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.example.demo.utility.DataConversionUtils.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpettatoreDTO implements Dto {
  String id;

  String nome;

  String cognome;

  String dataNascita;

  String biglietto;

  String maggiorenne;

  String etaSpettatore;

  String deleted;

  @Override
  public Spettatore toModel() {
    return Spettatore.builder()
        .id(stringToLong(id))
        .nome(nome)
        .cognome(cognome)
        .dataNascita(stringToLocalDateTime(dataNascita))
        .biglietto(stringToLong(biglietto))
        .maggiorenne(stringToBoolean(maggiorenne))
        .etaSpettatore(stringToInteger(etaSpettatore))
        .deleted(stringToBoolean(deleted))
        .build();
  }
}
