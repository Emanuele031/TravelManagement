package it.epicode.TravelManagement.service;

import it.epicode.TravelManagement.dto.ViaggioDTO;
import it.epicode.TravelManagement.entities.Viaggio;
import it.epicode.TravelManagement.Exception.ResourceNotFoundException;
import it.epicode.TravelManagement.repository.ViaggioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViaggioService {

    private final ViaggioRepository viaggioRepository;

    public List<Viaggio> getAllViaggi() {
        return viaggioRepository.findAll();
    }

    public Viaggio createViaggio(ViaggioDTO viaggioDTO) {
        Viaggio viaggio = new Viaggio();
        viaggio.setDestinazione(viaggioDTO.getDestinazione());
        viaggio.setData(viaggioDTO.getData());
        viaggio.setStato(viaggioDTO.getStato());

        return viaggioRepository.save(viaggio);
    }

    public Viaggio updateViaggio(Long id, ViaggioDTO viaggioDTO) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viaggio non trovato con ID: " + id));

        viaggio.setDestinazione(viaggioDTO.getDestinazione());
        viaggio.setData(viaggioDTO.getData());
        viaggio.setStato(viaggioDTO.getStato());

        return viaggioRepository.save(viaggio);
    }

    public void deleteViaggio(Long id) {
        if (!viaggioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Viaggio non trovato con ID: " + id);
        }
        viaggioRepository.deleteById(id);
    }

    public Viaggio updateStatoViaggio(Long id, String stato) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viaggio non trovato con ID: " + id));
        viaggio.setStato(stato);
        return viaggioRepository.save(viaggio);
    }
}