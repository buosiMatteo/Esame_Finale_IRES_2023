package com.example.demo.model;

import com.example.demo.dto.BigliettoDTO;
import com.example.demo.dto.archetype.Dto;
import com.example.demo.dto.archetype.Model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.example.demo.utility.DataConversionUtils.*;

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
  private Long id;

  @Column(name = "numero_sala")
  private Long numeroSala;

  @Column(name = "prezzo")
  private Double prezzo;

  @Column(name = "deleted")
  @Builder.Default
  private Boolean deleted = false;

  @Override
  public BigliettoDTO toDto() {
    return BigliettoDTO.builder()
        .id(numberToString(id))
        .numeroSala(numberToString(numeroSala))
        .prezzo(numberToString(prezzo))
        .deleted(booleanToString(deleted))
        .build();
  }

  public Double sconto(LocalDateTime dataNascita) {
    if (LocalDateTime.now().getYear() - dataNascita.getYear() < 5)
      return prezzo / 2.0;
    if (LocalDateTime.now().getYear() - dataNascita.getYear() > 70)
      return prezzo*9.0/10.0;
    return prezzo;
  }
}
