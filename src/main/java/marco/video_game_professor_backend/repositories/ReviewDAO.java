package marco.video_game_professor_backend.repositories;

import marco.video_game_professor_backend.entities.Review;
import marco.video_game_professor_backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewDAO extends JpaRepository<Review, Long> {
    List<Review> findByGameId(long gameId);

    List<Review> getReviewsByUserId(Long userId);

    @Query("SELECT r FROM Review r ORDER BY r.date DESC")
    List<Review> findLast10ByOrderByDateDesc();

    boolean existsByUserAndGameId(User user, long gameId);

}
