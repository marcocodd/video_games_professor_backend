package marco.video_game_professor_backend.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotEmpty(message = "L'username non può essere vuoto")
        @Size(min = 3, max = 10, message = "L'username deve essere almeno 3 caratteri e massimo 10")
        String username,
        @NotEmpty(message = "L'email è obbligatoria")
        @Email(message = "Email inserita non valida")
        String email,
        @NotEmpty(message = "La password è obbligatoria")
        @Size(min = 8, message = "La password deve contenere almeno 8 caratteri")
        String password) {
}
