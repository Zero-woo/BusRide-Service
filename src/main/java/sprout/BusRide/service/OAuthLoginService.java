package sprout.BusRide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sprout.BusRide.OAuth.OAuthInfoResponse;
import sprout.BusRide.OAuth.OAuthLoginParams;
import sprout.BusRide.OAuth.RequestOAuthInfoService;
import sprout.BusRide.domain.Member;
import sprout.BusRide.jwt.AuthTokens;
import sprout.BusRide.jwt.AuthTokensGenerator;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {

    private final LoginService loginService;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params){
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Optional<String> memberId = loginService.findByEmail(oAuthInfoResponse.getEmail());
        if (memberId.isPresent()) {
            return authTokensGenerator.generate(memberId.get());
        } else {
            Member member = Member.builder()
                    .email(oAuthInfoResponse.getEmail())
                    .nickname(oAuthInfoResponse.getNickname())
                    .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                    .build();
            String newMemberId = null;
            try {
                newMemberId = loginService.save(member);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return authTokensGenerator.generate(newMemberId);
        }

    }


}
