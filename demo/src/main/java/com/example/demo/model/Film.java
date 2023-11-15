package com.example.demo.model;

import com.example.demo.dto.FilmDTO;
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
@Table(name = "film")
@SQLDelete(sql = "UPDATE film SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Film implements Model {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "titolo")
  private String titolo;

  @Column(name = "autore")
  private String autore;

  @Column(name = "produttore")
  private String produttore;

  @Column(name = "genere")
  private String genere;

  @Column(name = "eta_minima")
  private Long etaMinima;

  @Column(name = "durata")
  private Long durata;

  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;

  @Override
  public FilmDTO toDto() {
    return FilmDTO.builder()
        .id(numberToString(id))
        .titolo(titolo)
        .autore(autore)
        .produttore(produttore)
        .genere(genere)
        .etaMinima(numberToString(etaMinima))
        .durata(numberToString(durata))
        .deleted(booleanToString(deleted))
        .build();
  }
}
