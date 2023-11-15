package com.example.demo.model;

import com.example.demo.dto.SpettatoreDTO;
import com.example.demo.dto.archetype.Dto;
import com.example.demo.dto.archetype.Model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

import static com.example.demo.utility.DataConversionUtils.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact")
@SQLDelete(sql = "UPDATE contact SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Spettatore implements Model {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  Long id;

  @Column(name = "nome")
  String nome;

  @Column(name = "cognome")
  String cognome;

  @Column(name = "data_nascita")
  LocalDateTime dataNascita;

  @OneToOne
  @MapsId("biglietto")
  @Column(name = "biglietto")
  Long biglietto;

  @Column(name = "maggiorenne")
  @Builder.Default
  Boolean maggiorenne = ((LocalDateTime.now().getYear()) - dataNascita.getYear() >= 18) ? true : false;

  @Column(name = "eta_spettatore")
  @Builder.Default
  Integer etaSpettatore = LocalDateTime.now().getYear() - dataNascita.getYear();

  @Column(name = "deleted")
  @Builder.Default
  Boolean deleted = false;

  @Override
  public SpettatoreDTO toDto() {
    return SpettatoreDTO.builder()
        .id(numberToString(id))
        .nome(nome)
        .cognome(cognome)
        .dataNascita(localDateTimeToString(dataNascita))
        .biglietto(numberToString(biglietto))
        .maggiorenne(booleanToString(maggiorenne))
        .etaSpettatore(numberToString(etaSpettatore))
        .deleted(booleanToString(deleted))
        .build();
  }
}
