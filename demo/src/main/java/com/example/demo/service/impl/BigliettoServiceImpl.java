package com.example.demo.service.impl;

import com.example.demo.exception.IdMustBeNullException;
import com.example.demo.exception.IdMustNotBeNullException;
import com.example.demo.model.Biglietto;
import com.example.demo.repos.BigliettoRepository;
import com.example.demo.service.BigliettoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class BigliettoServiceImpl implements BigliettoService {

  BigliettoRepository bigliettoRepository;
  @Override
  public List<Biglietto> findAll() {
  return bigliettoRepository.findAll();
  }


  @Override
  public Biglietto insert(Biglietto biglietto) {
    if(biglietto.getId() != null && biglietto.getId() > 0) {
      throw new IdMustBeNullException();
    }
    return bigliettoRepository.save(biglietto);
  }

  @Override
  public Biglietto update(Biglietto biglietto) {
    if(biglietto.getId() == null || biglietto.getId() == 0) {
      throw new IdMustNotBeNullException();
    }
    return bigliettoRepository.save(biglietto);
  }

  @Override
  public Boolean deleteById(Long idBiglietto) {
    bigliettoRepository.deleteById(idBiglietto);
    return bigliettoRepository.findById(idBiglietto).isEmpty();
  }

  @Override
  public Biglietto findById(Long idBiglietto) {
    return bigliettoRepository.findById(idBiglietto).orElse(Biglietto.builder().build());
  }
}
