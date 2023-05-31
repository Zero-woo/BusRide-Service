package sprout.BusRide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sprout.BusRide.OAuth.OAuthInfoResponse;
import sprout.BusRide.OAuth.OAuthLoginParams;
import sprout.BusRide.OAuth.RequestOAuthInfoService;
import sprout.BusRide.jwt.AuthTokens;
import sprout.BusRide.jwt.AuthTokensGenerator;



@Service
@RequiredArgsConstructor
public class OAuthLoginService {

    private final MemberService memberService;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long memberId = memberService.findOrCreateMember(oAuthInfoResponse);
        return authTokensGenerator.generate(memberId);

    }

}


