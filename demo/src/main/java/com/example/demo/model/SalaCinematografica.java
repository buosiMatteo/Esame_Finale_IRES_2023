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
public class SalaCinematografica {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  Long id;

  @Column(name = "numero_spettatori")
  Long numeroSpettatori;

  @Column(name = "film")
  String film;

  @OneToMany
  @MapsId("numero_sala")
  @Column(name = "numero_sala")
  Long numeroSala;

  @Column(name = "deleted")
  @Builder.Default
  Boolean deleted = false;
}
