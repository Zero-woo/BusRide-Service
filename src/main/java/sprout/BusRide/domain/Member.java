package sprout.BusRide.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Member {

    private Long id;

    private String nickname;

    private String email;


    private OAuthProvider oAuthProvider;

    @Builder
    public Member(String nickname, String email, OAuthProvider oAuthProvider) {
        this.nickname = nickname;
        this.email = email;
        this.oAuthProvider = oAuthProvider;
    }
}
