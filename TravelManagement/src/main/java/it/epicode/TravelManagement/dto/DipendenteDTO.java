package it.epicode.TravelManagement.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DipendenteDTO {

    @NotBlank(message = "Username obbligatorio")
    private String username;

    @NotBlank(message = "Nome obbligatorio")
    @Size(min = 2, max = 50, message = "Il nome deve essere tra 2 e 50 caratteri")
    private String nome;

    @NotBlank(message = "Cognome obbligatorio")
    @Size(min = 2, max = 50, message = "Il cognome deve essere tra 2 e 50 caratteri")
    private String cognome;

    @NotBlank(message = "Email obbligatoria")
    @Email(message = "Formato email non valido")
    private String email;
}
