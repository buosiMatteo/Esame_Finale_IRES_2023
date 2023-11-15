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
@Table(name = "contact")
@SQLDelete(sql = "UPDATE contact SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Film implements Model {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  Long id;

  @Column(name = "titolo")
  String titolo;

  @Column(name = "autore")
  String autore;

  @Column(name = "produttore")
  String produttore;

  @Column(name = "genere")
  String genere;

  @Column(name = "eta_minima")
  Long etaMinima;

  @Column(name = "durata")
  Long durata;

  @Column(name = "deleted")
  @Builder.Default
  Boolean deleted = false;

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
