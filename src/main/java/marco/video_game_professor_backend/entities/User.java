package marco.video_game_professor_backend.entities;

import jakarta.persistence.*;
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
    private String password;
    @Setter
    private String avatar;
    private String role;

    @OneToMany(mappedBy = "user")
    private List<Review> review;


    public User(String userName, String email, String password, String avatar) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.role = "USER";
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role));
    }

}
