package marco.video_game_professor_backend.controllers;

import marco.video_game_professor_backend.exceptions.BadRequestException;
import marco.video_game_professor_backend.payloads.NewUserDTO;
import marco.video_game_professor_backend.payloads.NewUserRespDTO;
import marco.video_game_professor_backend.payloads.UserLoginDTO;
import marco.video_game_professor_backend.payloads.UserLoginRespDTO;
import marco.video_game_professor_backend.services.AuthService;
import marco.video_game_professor_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public UserLoginRespDTO login(@RequestBody @Validated UserLoginDTO payload) {
        return new UserLoginRespDTO(this.authService.authenticateUserAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserRespDTO saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return new NewUserRespDTO(this.userService.save(body).getId());

    }
}
