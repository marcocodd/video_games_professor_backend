package marco.video_game_professor_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @Setter
    private String gameTitle;


    public Review(String content, int rating, LocalDate date, User user, long gameId, String gameTitle) {
        this.content = content;
        this.rating = rating;
        this.date = date;
        this.user = user;
        this.gameId = gameId;
        this.gameTitle = gameTitle;
    }

    @JsonProperty("username")
    public String getUserName() {
        return user != null ? user.getUserName() : null;
    }

    @JsonProperty("avatarUrl")
    public String getAvatarUrl() {
        return user != null ? user.getAvatar() : null;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                ", date=" + date +
                ", gameId=" + gameId +
                ", username=" + getUserName() +
                ", avatarUrl=" + getAvatarUrl() +
                '}';
    }
}
