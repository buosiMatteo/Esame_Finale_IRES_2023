package com.example.demo.repos;

import com.example.demo.model.Biglietto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BigliettoRepository extends JpaRepository<Biglietto,Long> {
}
