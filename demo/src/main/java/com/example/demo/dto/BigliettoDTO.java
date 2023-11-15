package com.example.demo.dto;

import com.example.demo.dto.archetype.Dto;
import com.example.demo.dto.archetype.Model;
import com.example.demo.model.Biglietto;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
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
  private String id;

  private String numeroSala;

  private String prezzo;

  private String deleted;

  @Override
  public Biglietto toModel() {
    return Biglietto.builder()
        .id(stringToLong(id))
        .numeroSala(stringToLong(numeroSala))
        .prezzo(stringToDouble(prezzo))
        .deleted(stringToBoolean(deleted))
        .build();
  }
}
