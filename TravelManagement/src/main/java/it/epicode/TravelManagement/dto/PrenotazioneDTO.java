package it.epicode.TravelManagement.dto;

import lombok.*;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneDTO {

    @NotNull(message = "Il viaggio è obbligatorio")
    private Long viaggioId;

    @NotNull(message = "Il dipendente è obbligatorio")
    private Long dipendenteId;

    @NotNull(message = "La data richiesta è obbligatoria")
    @FutureOrPresent(message = "La data richiesta deve essere nel futuro o presente")
    private LocalDate dataRichiesta;

    private String note;
}