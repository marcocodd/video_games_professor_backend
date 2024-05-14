package marco.video_game_professor_backend.services;

import marco.video_game_professor_backend.entities.Review;
import marco.video_game_professor_backend.entities.User;
import marco.video_game_professor_backend.exceptions.NotFoundException;
import marco.video_game_professor_backend.payloads.NewReviewDTO;
import marco.video_game_professor_backend.repositories.ReviewDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewDAO reviewDAO;

    public Review findById(long reviewId) {
        return this.reviewDAO.findById(reviewId).orElseThrow(() -> new NotFoundException(reviewId));
    }

    public Review save(NewReviewDTO newReview, User currentUser, long gameId) {
        Review review = new Review(
                newReview.content(),
                newReview.rating(),
                LocalDate.now(),
                currentUser,
                gameId
        );
        return reviewDAO.save(review);
    }

    public List<Review> getReviewsByGameId(long gameId) {
        return reviewDAO.findByGameId(gameId);
    }

    public List<Review> getLast10Reviews() {
        return reviewDAO.findLast10ByOrderByDateDesc();
    }
}
