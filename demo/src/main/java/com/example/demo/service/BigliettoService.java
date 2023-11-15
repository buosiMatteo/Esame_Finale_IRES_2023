package com.example.demo.service;

import com.example.demo.model.Biglietto;
import com.example.demo.model.Cinema;

import java.util.List;

public interface BigliettoService {
  List<Biglietto> findAll();

  Biglietto insert(Biglietto biglietto);

  Biglietto update(Biglietto biglietto);

  Boolean deleteById(Long idBiglietto);

  Biglietto findById(Long idBiglietto);
}
