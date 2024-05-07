package marco.video_game_professor_backend.services;

import marco.video_game_professor_backend.entities.User;
import marco.video_game_professor_backend.exceptions.BadRequestException;
import marco.video_game_professor_backend.exceptions.NotFoundException;
import marco.video_game_professor_backend.payloads.NewUserDTO;
import marco.video_game_professor_backend.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    PasswordEncoder passwordEncoder;


    public User findById(long userId) {
        return this.userDAO.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public User findByEmail(String email) {
        return this.userDAO.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente non trovato per email: " + email));
    }

    public User save(NewUserDTO body) {
        this.userDAO.findByEmail(body.email()).ifPresent(
                user -> {
                    throw new BadRequestException("L'email " + body.email() + " è già in uso!");
                });
        this.userDAO.findByUserName(body.userName()).ifPresent(
                user -> {
                    throw new BadRequestException("L'username " + body.userName() + " è già in uso!");
                });

        User newUser = new User(body.userName(), body.email(), passwordEncoder.encode(body.password()),
                "https://ui-avatars.com/api/?name=" + body.userName());

        return userDAO.save(newUser);
    }

}
