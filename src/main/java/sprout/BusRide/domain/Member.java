package sprout.BusRide.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String email;

    @Enumerated(EnumType.STRING)
    private OAuthProvider oAuthProvider;

    @Builder
    public Member(String nickname, String email, OAuthProvider oAuthProvider) {
        this.nickname = nickname;
        this.email = email;
        this.oAuthProvider = oAuthProvider;
    }
}
