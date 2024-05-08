package marco.video_game_professor_backend.controllers;

import marco.video_game_professor_backend.entities.User;
import marco.video_game_professor_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping("/me")
    public User updateProfile(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody User updatedUser) {
        return this.userService.findByIdAndUpdate(currentAuthenticatedUser.getId(), updatedUser);
    }

    @PostMapping("/{userId}/avatar")
    public User uploadAvatar(@RequestParam("avatar") MultipartFile avatar, @PathVariable long userId) throws IOException {
        return this.userService.uploadAvatar(userId, avatar);

    }
}
