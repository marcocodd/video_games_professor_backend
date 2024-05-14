package marco.video_game_professor_backend.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewReviewDTO(

        String content,
        @NotNull(message = "Inserisci un voto da 1 a 10")
        @Size(min = 1, max = 10)
        int rating,
        @NotNull(message = "Il gameId è obbligatorio")
        long gameId) {
}
