package sprout.BusRide.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sprout.BusRide.domain.RideBell;
import sprout.BusRide.service.RideBellService;

import java.util.List;

@Tag(name = "RideBell", description = "승차벨에 관한 정보")
@RestController
@RequiredArgsConstructor
public class RideBellController {

    private final RideBellService rideBellService;

    @Operation(summary = "승차벨 요청", description = "승차벨 요청하기")
    @Parameters({
            @Parameter(name = "busStopId", description = "정류장 ID", example = "17945"),
            @Parameter(name = "message", description = "메시지", example = "저는 다리가 불편해요")
    })
    @PostMapping("/RideBell")
    public void requestRideBell(@RequestBody RideBell rideBell) {
        rideBellService.saveRideBell(rideBell);
    }

    @Operation(summary = "승차벨 정보", description = "승차벨 정보 가져오기")
    @GetMapping("/RideBell")
    public List<RideBell> AllRideBell() {
        return rideBellService.findRideBells();
    }
}
