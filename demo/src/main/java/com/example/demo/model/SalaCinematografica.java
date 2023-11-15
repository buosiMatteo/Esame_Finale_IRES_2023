package com.example.demo.model;

import com.example.demo.dto.SalaCinematograficaDTO;
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
@Table(name = "contact")
@SQLDelete(sql = "UPDATE contact SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class SalaCinematografica implements Model {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  Long id;

  @Column(name = "numero_spettatori")
  Long numeroSpettatori;

  @ManyToOne
  @MapsId("id_film")
  Long idFilm;

  @Column(name = "film")
  String film;

  @OneToMany
  @MapsId("numero_sala")
  @Column(name = "numero_sala")
  Long numeroSala;

  @Column(name = "deleted")
  @Builder.Default
  Boolean deleted = false;

  @Override
  public SalaCinematograficaDTO toDto() {
    return SalaCinematograficaDTO.builder()
        .id(numberToString(id))
        .numeroSpettatori(numberToString(numeroSpettatori))
        .idFilm(numberToString(idFilm))
        .film(film)
        .numeroSala(numberToString(numeroSala))
        .deleted(booleanToString(deleted))
        .build();
  }
}
