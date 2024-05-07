package marco.video_game_professor_backend.exceptions;

import java.time.LocalDateTime;

public record ErrorsResponseDTO(
        String message,
        LocalDateTime timestamp
) {
}
