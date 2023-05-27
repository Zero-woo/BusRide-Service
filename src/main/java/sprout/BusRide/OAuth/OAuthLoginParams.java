package sprout.BusRide.OAuth;

import org.springframework.util.MultiValueMap;
import sprout.BusRide.domain.OAuthProvider;

public interface OAuthLoginParams {

    OAuthProvider oAuthProvider();

    MultiValueMap<String, String> makeBody();
}
