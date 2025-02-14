package it.epicode.TravelManagement.controller;

import it.epicode.TravelManagement.dto.DipendenteDTO;
import it.epicode.TravelManagement.entities.Dipendente;
import it.epicode.TravelManagement.service.DipendenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/dipendenti")
@RequiredArgsConstructor
public class DipendenteController {

    private final DipendenteService dipendenteService;

    @GetMapping
    public ResponseEntity<List<Dipendente>> getAllDipendenti() {
        return ResponseEntity.ok(dipendenteService.getAllDipendenti());
    }

    @PostMapping
    public ResponseEntity<Dipendente> createDipendente(@Valid @RequestBody DipendenteDTO dipendenteDTO) {
        return ResponseEntity.ok(dipendenteService.createDipendente(dipendenteDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dipendente> updateDipendente(@PathVariable Long id, @Valid @RequestBody DipendenteDTO dipendenteDTO) {
        return ResponseEntity.ok(dipendenteService.updateDipendente(id, dipendenteDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDipendente(@PathVariable Long id) {
        dipendenteService.deleteDipendente(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/upload")
    public ResponseEntity<Dipendente> uploadProfileImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            Dipendente updatedDipendente = dipendenteService.uploadProfileImage(id, file);
            return ResponseEntity.ok(updatedDipendente);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}