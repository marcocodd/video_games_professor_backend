package marco.video_game_professor_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
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

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                ", date=" + date +
                ", gameId=" + gameId +
                '}';
    }
}
