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
@Table(name = "sala_cinematografica")
@SQLDelete(sql = "UPDATE sala_cinematografica SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class SalaCinematografica implements Model {

  public static Long NUMERO_MAX_SPETTATORI = 100L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "numero_spettatori")
  private Long numeroSpettatori;

  @ManyToOne
  @MapsId("idFilm")
  @JoinColumn(name = "id_film")
  private Film idFilm;

  @Column(name = "film")
  private String film;

  @Column(name = "numero_sala")
  private Long numeroSala;

  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;

  @Override
  public SalaCinematograficaDTO toDto() {
    return SalaCinematograficaDTO.builder()
        .id(numberToString(id))
        .numeroSpettatori(numberToString(numeroSpettatori))
        .idFilm(numberToString(idFilm.getId()))
        .film(film)
        .numeroSala(numberToString(numeroSala))
        .deleted(booleanToString(deleted))
        .build();
  }


}
