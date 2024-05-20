package marco.video_game_professor_backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "user_name")
    @Setter
    private String userName;
    @Setter
    private String email;
    @Setter
    @JsonIgnore
    private String password;
    @Setter
    private String avatar;
    @Getter(AccessLevel.NONE)
    private String role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)

    private List<Review> review;


    public User(String userName, String email, String password, String avatar) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.role = "USER";
    }

    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", review=" + review +
                '}';
    }
}
