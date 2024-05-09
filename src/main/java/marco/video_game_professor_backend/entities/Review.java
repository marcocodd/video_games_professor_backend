package marco.video_game_professor_backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @ManyToOne
    private User user;
}
