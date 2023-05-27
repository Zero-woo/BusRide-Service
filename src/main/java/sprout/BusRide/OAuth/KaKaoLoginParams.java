package sprout.BusRide.OAuth;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import sprout.BusRide.domain.OAuthProvider;

public class KaKaoLoginParams implements OAuthLoginParams {
    private String authorizationCode;

    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.KAKAO;
    }

    @Override
    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", authorizationCode);
        return body;
    }

}
