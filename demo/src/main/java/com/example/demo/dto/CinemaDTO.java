package com.example.demo.dto;

import com.example.demo.dto.archetype.Dto;
import com.example.demo.dto.archetype.Model;
import com.example.demo.model.Cinema;
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
public class CinemaDTO implements Dto {
  String id;

  String nomeCinema;

  String numeroSale;

  String address;

  String city;

  String email;

  String notes;

  String deleted;

  @Override
  public Cinema toModel() {
    return Cinema.builder()
        .id(stringToLong(id))
        .nomeCinema(nomeCinema)
        .numeroSale(stringToLong(numeroSale))
        .address(address)
        .city(city)
        .email(email)
        .notes(notes)
        .deleted(stringToBoolean(deleted))
        .build();
  }
}
