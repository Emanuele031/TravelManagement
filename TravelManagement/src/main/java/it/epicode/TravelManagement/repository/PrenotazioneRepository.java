package it.epicode.TravelManagement.repository;

import it.epicode.TravelManagement.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    boolean existsByDipendenteIdAndDataRichiesta(Long dipendenteId, LocalDate dataRichiesta);
}

