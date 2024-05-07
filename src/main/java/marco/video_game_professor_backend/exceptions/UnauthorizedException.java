package marco.video_game_professor_backend.exceptions;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("Utente non autorizzato.");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
