package sprout.BusRide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sprout.BusRide.OAuth.OAuthInfoResponse;
import sprout.BusRide.domain.Member;
import sprout.BusRide.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void saveMember(Member member) {
        memberRepository.save(member);
    }

    public Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return memberRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(Member::getId)
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }

    private Long newMember(OAuthInfoResponse oAuthInfoResponse) {
        Member member = Member.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickname(oAuthInfoResponse.getNickname())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();
        return memberRepository.save(member).getId();
    }




}
