package it.epicode.TravelManagement.controller;

import it.epicode.TravelManagement.dto.PrenotazioneDTO;
import it.epicode.TravelManagement.entities.Prenotazione;
import it.epicode.TravelManagement.service.PrenotazioneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
@RequiredArgsConstructor
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;

    @GetMapping
    public ResponseEntity<List<Prenotazione>> getAllPrenotazioni() {
        return ResponseEntity.ok(prenotazioneService.getAllPrenotazioni());
    }

    @PostMapping
    public ResponseEntity<Prenotazione> createPrenotazione(@Valid @RequestBody PrenotazioneDTO prenotazioneDTO) {
        return ResponseEntity.ok(prenotazioneService.createPrenotazione(prenotazioneDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prenotazione> updatePrenotazione(@PathVariable Long id, @Valid @RequestBody PrenotazioneDTO prenotazioneDTO) {
        return ResponseEntity.ok(prenotazioneService.updatePrenotazione(id, prenotazioneDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrenotazione(@PathVariable Long id) {
        prenotazioneService.deletePrenotazione(id);
        return ResponseEntity.noContent().build();
    }
}
