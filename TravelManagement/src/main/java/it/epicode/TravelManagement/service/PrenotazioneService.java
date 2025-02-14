package it.epicode.TravelManagement.service;

import it.epicode.TravelManagement.dto.PrenotazioneDTO;
import it.epicode.TravelManagement.entities.Prenotazione;
import it.epicode.TravelManagement.entities.Viaggio;
import it.epicode.TravelManagement.entities.Dipendente;
import it.epicode.TravelManagement.Exception.ResourceNotFoundException;
import it.epicode.TravelManagement.repository.PrenotazioneRepository;
import it.epicode.TravelManagement.repository.ViaggioRepository;
import it.epicode.TravelManagement.repository.DipendenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final ViaggioRepository viaggioRepository;
    private final DipendenteRepository dipendenteRepository;

    public List<Prenotazione> getAllPrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    public boolean isDipendenteDisponibile(Long dipendenteId, LocalDate dataRichiesta) {
        return !prenotazioneRepository.existsByDipendenteIdAndDataRichiesta(dipendenteId, dataRichiesta);
    }

    public Prenotazione createPrenotazione(PrenotazioneDTO prenotazioneDTO) {
        if (!isDipendenteDisponibile(prenotazioneDTO.getDipendenteId(), prenotazioneDTO.getDataRichiesta())) {
            throw new IllegalStateException("Dipendente già impegnato in un'altra prenotazione per questa data.");
        }

        Viaggio viaggio = viaggioRepository.findById(prenotazioneDTO.getViaggioId())
                .orElseThrow(() -> new ResourceNotFoundException("Viaggio non trovato con ID: " + prenotazioneDTO.getViaggioId()));

        Dipendente dipendente = dipendenteRepository.findById(prenotazioneDTO.getDipendenteId())
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente non trovato con ID: " + prenotazioneDTO.getDipendenteId()));

        Prenotazione prenotazione = new Prenotazione(null, viaggio, dipendente, prenotazioneDTO.getDataRichiesta(), prenotazioneDTO.getNote());
        return prenotazioneRepository.save(prenotazione);
    }

    public Prenotazione updatePrenotazione(Long id, PrenotazioneDTO prenotazioneDTO) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prenotazione non trovata con ID: " + id));

        Viaggio viaggio = viaggioRepository.findById(prenotazioneDTO.getViaggioId())
                .orElseThrow(() -> new ResourceNotFoundException("Viaggio non trovato con ID: " + prenotazioneDTO.getViaggioId()));

        Dipendente dipendente = dipendenteRepository.findById(prenotazioneDTO.getDipendenteId())
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente non trovato con ID: " + prenotazioneDTO.getDipendenteId()));

        prenotazione.setViaggio(viaggio);
        prenotazione.setDipendente(dipendente);
        prenotazione.setDataRichiesta(prenotazioneDTO.getDataRichiesta());
        prenotazione.setNote(prenotazioneDTO.getNote());

        return prenotazioneRepository.save(prenotazione);
    }

    public void deletePrenotazione(Long id) {
        if (!prenotazioneRepository.existsById(id)) {
            throw new ResourceNotFoundException("Prenotazione non trovata con ID: " + id);
        }
        prenotazioneRepository.deleteById(id);
    }
}
