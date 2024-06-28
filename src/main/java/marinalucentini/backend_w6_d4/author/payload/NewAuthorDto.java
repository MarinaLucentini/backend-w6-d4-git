package marinalucentini.backend_w6_d4.author.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewAuthorDto(
        @NotEmpty(message = "Il nome è obbligatorio!")
                @Size (min = 3, max = 30, message = "Il nome deve avere un numero di caratteri compreso tra 3 e 30")
        String name,
        @NotEmpty(message = "Il cognome è obbligatorio")
                @Size(min = 3, max =40, message = "Il cognome deve avere un numero di caratteri compreso tra 3 e 40")
        String lastName,
        @Email(message = "L'email inserita non è valida")
                @NotEmpty(message = "L'email è obbligatoria")
        String email,
        @NotNull(message = "La data di nascita è obbligatoria")
        LocalDate dateOfBirth) {

}
