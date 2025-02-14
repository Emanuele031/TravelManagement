package it.epicode.TravelManagement.dto;

import lombok.*;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViaggioDTO {

    @NotBlank(message = "Destinazione obbligatoria")
    private String destinazione;

    @NotNull(message = "La data del viaggio è obbligatoria")
    @FutureOrPresent(message = "La data del viaggio deve essere nel futuro o presente")
    private LocalDate data;

    @Pattern(regexp = "^(in programma|completato)$", message = "Lo stato deve essere 'in programma' o 'completato'")
    private String stato;
}