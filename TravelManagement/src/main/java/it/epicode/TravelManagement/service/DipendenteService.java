package it.epicode.TravelManagement.service;



import com.cloudinary.Cloudinary;
import it.epicode.TravelManagement.dto.DipendenteDTO;
import it.epicode.TravelManagement.entities.Dipendente;
import it.epicode.TravelManagement.exception.ResourceNotFoundException;
import it.epicode.TravelManagement.repository.DipendenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DipendenteService {

    private final DipendenteRepository dipendenteRepository;
    private final Cloudinary cloudinary;

    public List<Dipendente> getAllDipendenti() {
        return dipendenteRepository.findAll();
    }

    public Dipendente createDipendente(DipendenteDTO dipendenteDTO) {
        Dipendente dipendente = new Dipendente(null, dipendenteDTO.getUsername(), dipendenteDTO.getNome(),
                dipendenteDTO.getCognome(), dipendenteDTO.getEmail(), null);
        return dipendenteRepository.save(dipendente);
    }

    public Dipendente updateDipendente(Long id, DipendenteDTO dipendenteDTO) {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente non trovato con ID: " + id));

        dipendente.setUsername(dipendenteDTO.getUsername());
        dipendente.setNome(dipendenteDTO.getNome());
        dipendente.setCognome(dipendenteDTO.getCognome());
        dipendente.setEmail(dipendenteDTO.getEmail());

        return dipendenteRepository.save(dipendente);
    }

    public void deleteDipendente(Long id) {
        if (!dipendenteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Dipendente non trovato con ID: " + id);
        }
        dipendenteRepository.deleteById(id);
    }

    public Dipendente uploadProfileImage(Long id, MultipartFile file) throws IOException {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dipendente non trovato con ID: " + id));

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), Map.of("folder", "dipendenti"));

        String imageUrl = (String) uploadResult.get("url");
        dipendente.setImmagineProfilo(imageUrl);

        return dipendenteRepository.save(dipendente);
    }
}
