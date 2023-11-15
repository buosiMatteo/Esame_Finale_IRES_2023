package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact")
@SQLDelete(sql = "UPDATE contact SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Film {
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
}
