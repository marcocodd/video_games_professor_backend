package marco.video_game_professor_backend.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Il record con id: " + id + " non è stato trovato!");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
