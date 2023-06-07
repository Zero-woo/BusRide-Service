package sprout.BusRide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sprout.BusRide.domain.Member;
import sprout.BusRide.domain.RideBell;
import sprout.BusRide.dto.RideBellDto;
import sprout.BusRide.jwt.AuthTokensGenerator;
import sprout.BusRide.repository.MemberRepository;
import sprout.BusRide.repository.RideBellRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RideBellService {

    private final RideBellRepository rideBellRepository;
    private final MemberRepository memberRepository;
    private final AuthTokensGenerator authTokensGenerator;



    public void saveRideBell(RideBellDto rideBellDto) {
//        Long memberId = authTokensGenerator.extractMemberId(accessToken);
//        String name = memberRepository.findById(memberId)
//                .map(Member::getNickname)
//                .orElse("익명");

        RideBell rideBell = RideBell.builder()
                .busNumber(rideBellDto.getBusNumber())
                .busStopName(rideBellDto.getBusStopName())
                .passengerType(rideBellDto.getPassengerType())
                .seatType(rideBellDto.getSeatType())
                .message(rideBellDto.getMessage()).build();

        rideBellRepository.save(rideBell);
    }

    public List<RideBell> findRideBells() {
        return rideBellRepository.findAll();
    }

}
