package marco.video_game_professor_backend.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException() {
        super("Errore interno del server.");
    }

    public InternalServerErrorException(String message) {
        super(message);
    }
}
