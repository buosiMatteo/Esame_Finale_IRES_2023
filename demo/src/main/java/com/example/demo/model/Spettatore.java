package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact")
@SQLDelete(sql = "UPDATE contact SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Spettatore {
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
}
