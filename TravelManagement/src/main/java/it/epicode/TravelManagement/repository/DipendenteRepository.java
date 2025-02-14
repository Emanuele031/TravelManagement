package it.epicode.TravelManagement.repository;

import it.epicode.TravelManagement.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {}
