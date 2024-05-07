package marco.video_game_professor_backend.services;

import marco.video_game_professor_backend.entities.User;
import marco.video_game_professor_backend.exceptions.UnauthorizedException;
import marco.video_game_professor_backend.payloads.UserLoginDTO;
import marco.video_game_professor_backend.security.JWTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private JWTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateUserAndGenerateToken(UserLoginDTO payload) {

        User user = this.userService.findByEmail(payload.email());
        if (bcrypt.matches(payload.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {

            throw new UnauthorizedException("Credenziali non valide! Effettua di nuovo il login!");
        }


    }
}
