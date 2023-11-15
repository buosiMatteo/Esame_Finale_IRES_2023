package com.example.demo.model;

import com.example.demo.dto.BigliettoDTO;
import com.example.demo.dto.archetype.Dto;
import com.example.demo.dto.archetype.Model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;

import static com.example.demo.utility.DataConversionUtils.bigDecimalToString;
import static com.example.demo.utility.DataConversionUtils.numberToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "biglietto")
@SQLDelete(sql = "UPDATE biglietto SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Biglietto implements Model {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  Long id;

  @Column(name = "numero_sala")
  Long numeroSala;

  @Column(name = "prezzo")
  BigDecimal prezzo;

  @Column(name = "deleted")
  @Builder.Default
  Boolean deleted = false;

  @Override
  public BigliettoDTO toDto() {
    return BigliettoDTO.builder()
        .id(numberToString(id))
        .numeroSala(numberToString(numeroSala))
        .prezzo(bigDecimalToString(prezzo))
        .build();
  }
}
