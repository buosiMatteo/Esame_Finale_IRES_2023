package com.example.demo.dto;

import com.example.demo.dto.archetype.Dto;
import com.example.demo.dto.archetype.Model;
import com.example.demo.model.Film;
import jakarta.persistence.Column;
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
public class FilmDTO implements Dto {
  private String id;

  private String titolo;

  private String autore;

  private String produttore;

  private String genere;

  private String etaMinima;

  private String durata;

  private String deleted;

  @Override
  public Film toModel() {
    return Film.builder()
        .id(stringToLong(id))
        .titolo(titolo)
        .autore(autore)
        .produttore(produttore)
        .genere(genere)
        .etaMinima(stringToLong(etaMinima))
        .durata(stringToLong(durata))
        .deleted(stringToBoolean(deleted))
        .build();
  }
}
