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
public class Cinema {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  Long id;

  @Column(name = "nome_cinema")
  String nomeCinema;

  @Column(name = "numero_sale")
  Long numeroSale;

  @Column(name = "address")
  String address;

  @Column(name = "city")
  String city;

  @Column(name = "info_point_email")
  String email;

  @Column(name = "notes")
  String notes;

  @Column(name = "deleted")
  @Builder.Default
  Boolean deleted = false;
}
