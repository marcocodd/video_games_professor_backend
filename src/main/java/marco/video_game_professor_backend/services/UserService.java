package marco.video_game_professor_backend.services;

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
}
