package com.example.demo.dto;

import com.example.demo.dto.archetype.Dto;
import com.example.demo.dto.archetype.Model;
import com.example.demo.model.Biglietto;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.example.demo.utility.DataConversionUtils.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BigliettoDTO implements Dto {
  String id;

  String numeroSala;

  String prezzo;

  String deleted;

  @Override
  public Biglietto toModel() {
    return Biglietto.builder()
        .id(stringToLong(id))
        .numeroSala(stringToLong(numeroSala))
        .prezzo(stringToBigDecimal(prezzo))
        .deleted(stringToBoolean(deleted))
        .build();
  }
}
