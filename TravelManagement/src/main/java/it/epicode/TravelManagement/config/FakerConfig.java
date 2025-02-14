package it.epicode.TravelManagement.config;



import com.github.javafaker.Faker;
import it.epicode.TravelManagement.entities.Dipendente;
import it.epicode.TravelManagement.entities.Viaggio;
import it.epicode.TravelManagement.repository.DipendenteRepository;
import it.epicode.TravelManagement.repository.ViaggioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class FakerConfig implements CommandLineRunner {

    private final DipendenteRepository dipendenteRepository;
    private final ViaggioRepository viaggioRepository;
    private final Faker faker = new Faker();

    @Override
    public void run(String... args) {

        if (dipendenteRepository.count() == 0) {
            IntStream.range(1, 5).forEach(i -> {
                Dipendente dipendente = new Dipendente(null,
                        faker.name().username(),
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        null);
                dipendenteRepository.save(dipendente);
            });
        }

        if (viaggioRepository.count() == 0) {
            IntStream.range(1, 5).forEach(i -> {
                Viaggio viaggio = new Viaggio(null,
                        faker.country().capital(),
                        LocalDate.now().plusDays(faker.number().numberBetween(1, 30)),
                        "in programma");
                viaggioRepository.save(viaggio);
            });
        }
    }
}

