package sprout.BusRide.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sprout.BusRide.domain.RideBell;
import sprout.BusRide.service.RideBellService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RideBellController {

    private final RideBellService rideBellService;

    @Operation(summary = "승차벨 요청", description = "승차벨 요청하기")
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
