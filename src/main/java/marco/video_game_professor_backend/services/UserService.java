package marco.video_game_professor_backend.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import marco.video_game_professor_backend.entities.User;
import marco.video_game_professor_backend.exceptions.BadRequestException;
import marco.video_game_professor_backend.exceptions.NotFoundException;
import marco.video_game_professor_backend.payloads.NewUserDTO;
import marco.video_game_professor_backend.repositories.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    PasswordEncoder passwordEncoder;


    public User findById(long userId) {
        return this.userDAO.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }


    public User findByIdWithReviews(long id) {
        User user = this.findById(id);
        return user;
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
        this.userDAO.findByUserName(body.username()).ifPresent(
                user -> {
                    throw new BadRequestException("L'username " + body.username() + " è già in uso!");
                });

        User newUser = new User(body.username(), body.email(), passwordEncoder.encode(body.password()),
                "https://ui-avatars.com/api/?name=" + body.username());

        return userDAO.save(newUser);
    }

    public User findByIdAndUpdate(long userId, User modifiedUser) {
        User found = this.findById(userId);
        found.setUserName(modifiedUser.getUserName());
        found.setEmail(modifiedUser.getEmail());
        found.setPassword(modifiedUser.getPassword());
        found.setAvatar(modifiedUser.getAvatar());
        return this.userDAO.save(found);
    }

    public User uploadAvatar(long userId, MultipartFile image) throws IOException {
        User found = this.findById(userId);
        String url = (String) cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
        found.setAvatar(url);
        return userDAO.save(found);
    }

}
