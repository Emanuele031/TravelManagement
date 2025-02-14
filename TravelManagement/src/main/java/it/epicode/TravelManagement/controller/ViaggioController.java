package it.epicode.TravelManagement.controller;

import it.epicode.TravelManagement.dto.ViaggioDTO;
import it.epicode.TravelManagement.entities.Viaggio;
import it.epicode.TravelManagement.service.ViaggioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viaggi")
@RequiredArgsConstructor
public class ViaggioController {

    private final ViaggioService viaggioService;

    @GetMapping
    public ResponseEntity<List<Viaggio>> getAllViaggi() {
        return ResponseEntity.ok(viaggioService.getAllViaggi());
    }

    @PostMapping
    public ResponseEntity<Viaggio> createViaggio(@Valid @RequestBody ViaggioDTO viaggioDTO) {
        return ResponseEntity.ok(viaggioService.createViaggio(viaggioDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Viaggio> updateViaggio(@PathVariable Long id, @Valid @RequestBody ViaggioDTO viaggioDTO) {
        return ResponseEntity.ok(viaggioService.updateViaggio(id, viaggioDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteViaggio(@PathVariable Long id) {
        viaggioService.deleteViaggio(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/stato")
    public ResponseEntity<Viaggio> updateStatoViaggio(@PathVariable Long id, @RequestParam String stato) {
        return ResponseEntity.ok(viaggioService.updateStatoViaggio(id, stato));
    }
}
