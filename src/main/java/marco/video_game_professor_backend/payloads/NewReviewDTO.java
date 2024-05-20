package marco.video_game_professor_backend.payloads;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewReviewDTO(

        String content,
        @NotNull(message = "Inserisci un voto da 1 a 10")
        @Min(value = 1, message = "Rating must be at least 1")
        @Max(value = 10, message = "Rating must be at most 10")
        int rating,
        @NotNull(message = "Il gameId è obbligatorio")
        long gameId,
        @NotEmpty
        String gameTitle
) {
}
