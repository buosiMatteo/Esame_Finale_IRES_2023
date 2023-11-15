package com.example.demo.dto;

import com.example.demo.dto.archetype.Dto;
import com.example.demo.dto.archetype.Model;
import com.example.demo.model.Film;
import com.example.demo.model.SalaCinematografica;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.example.demo.utility.DataConversionUtils.stringToBoolean;
import static com.example.demo.utility.DataConversionUtils.stringToLong;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaCinematograficaDTO implements Dto {
  private String id;

  private String numeroSpettatori;

  private String film;

  private String numeroSala;

  private String deleted;

  @Override
  public SalaCinematografica toModel() {
    return SalaCinematografica.builder()
        .id(stringToLong(id))
        .film(film)
        .numeroSala(stringToLong(numeroSala))
        .numeroSpettatori(stringToLong(numeroSpettatori))
        .deleted(stringToBoolean(deleted))
        .build();
  }
}
