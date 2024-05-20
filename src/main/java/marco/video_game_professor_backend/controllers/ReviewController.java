package marco.video_game_professor_backend.controllers;

import marco.video_game_professor_backend.entities.Review;
import marco.video_game_professor_backend.entities.User;
import marco.video_game_professor_backend.payloads.NewReviewDTO;
import marco.video_game_professor_backend.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public Review save(@AuthenticationPrincipal User currentUser, @RequestBody @Validated NewReviewDTO newReview) {
        return reviewService.save(newReview, currentUser, newReview.gameId(), newReview.gameTitle());
    }

    @GetMapping("/{gameId}")
    public List<Review> getReviewsByGameId(@PathVariable long gameId) {
        return reviewService.getReviewsByGameId(gameId);
    }

    @GetMapping("/lastreviews")
    public List<Review> getLast10Reviews() {
        return reviewService.getLast10Reviews();
    }

}
