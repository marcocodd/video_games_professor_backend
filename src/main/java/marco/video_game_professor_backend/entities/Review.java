package marco.video_game_professor_backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "reviews")
@NoArgsConstructor
@Getter
public class Review {
    @Id
    @GeneratedValue
    private long id;
    @Setter
    private String content;
    @Setter
    private int rating;
    @Setter
    private LocalDate date;
    @ManyToOne
    private User user;
    @Setter
    private long gameId;


    public Review(String content, int rating, LocalDate date, User user, long gameId) {
        this.content = content;
        this.rating = rating;
        this.date = date;
        this.user = user;
        this.gameId = gameId;
    }
}
