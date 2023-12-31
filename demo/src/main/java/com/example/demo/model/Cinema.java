package com.example.demo.model;

import com.example.demo.dto.CinemaDTO;
import com.example.demo.dto.archetype.Dto;
import com.example.demo.dto.archetype.Model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import static com.example.demo.utility.DataConversionUtils.booleanToString;
import static com.example.demo.utility.DataConversionUtils.numberToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cinema")
@SQLDelete(sql = "UPDATE cinema SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Cinema implements Model {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "nome_cinema")
  private String nomeCinema;

  @Column(name = "numero_sale")
  private Long numeroSale;

  @Column(name = "address")
  private String address;

  @Column(name = "city")
  private String city;

  @Column(name = "info_point_email")
  private String email;

  @Column(name = "notes")
  private String notes;

  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;

  @Override
  public CinemaDTO toDto() {
    return CinemaDTO.builder()
        .id(numberToString(id))
        .nomeCinema(nomeCinema)
        .numeroSale(numberToString(numeroSale))
        .address(address)
        .city(city)
        .email(email)
        .notes(notes)
        .deleted(booleanToString(deleted))
        .build();
  }
}
