package sprout.BusRide.OAuth;

import sprout.BusRide.domain.OAuthProvider;

public interface OAuthApiClient {

    OAuthProvider oAuthProvider();

    String requestAccessToken(OAuthLoginParams params);

    OAuthInfoResponse requestOauthInfo(String accessToken);
}
