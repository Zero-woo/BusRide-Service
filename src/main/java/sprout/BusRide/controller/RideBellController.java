package sprout.BusRide.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sprout.BusRide.domain.RideBell;
import sprout.BusRide.dto.RideBellDto;
import sprout.BusRide.service.RideBellService;

import java.util.List;

@Tag(name = "RideBell", description = "승차벨에 관한 정보")
@RestController
@RequiredArgsConstructor
public class RideBellController {

    private final RideBellService rideBellService;

    @Operation(summary = "승차벨 요청", description = "승차벨 요청하기")
    @Parameters({
            @Parameter(name = "busNumber", description = "버스번호", example = "6515"),
            @Parameter(name = "butStopName", description = "버스정류장 이름", example = "신도림역"),
            @Parameter(name = "passengerType", description = "승객유형", example = "장애인"),
            @Parameter(name = "seatType", description = "좌석유형", example = "장애인좌석"),
            @Parameter(name = "message", description = "메시지", example = "저는 다리가 불편해요")
    })
    @PostMapping("/RideBell")
    public void requestRideBell(@RequestBody RideBellDto rideBellDto) {
        rideBellService.saveRideBell(rideBellDto);
    }

    @Operation(summary = "승차벨 정보", description = "승차벨 정보 가져오기")
    @GetMapping("/RideBell")
    public List<RideBell> AllRideBell() {
        return rideBellService.findRideBells();
    }


}
