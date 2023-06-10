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
            @Parameter(name = "message", description = "메시지", example = "저는 다리가 불편해요")
    })
    @PostMapping("/RideBell")
    public Long requestRideBell(@RequestBody RideBellDto rideBellDto) {
        return rideBellService.saveRideBell(rideBellDto);
    }

    @Operation(summary = "승차벨 정보", description = "승차벨 정보 가져오기")
    @GetMapping("/RideBell")
    public List<RideBell> AllRideBell() {
        return rideBellService.findRideBells();
    }

    @Operation(summary = "버스번호로 승차벨 확인")
    @GetMapping("RideBell/BusNum/{BusNumber}")
    @Parameter(name = "BusNumber",description = "버스번호", example = "600")
    public List<RideBell> RideBellByBus(@PathVariable("BusNumber") String busNumber) {
        return rideBellService.findRideBellByBus(busNumber);
    }

    @Operation(summary = "버스정류장 번호로 승차벨 확인", description = "버스정류장 번호로 승차벨 확인")
    @GetMapping("RideBell/BusStop/{BusStopNumber}")
    @Parameter(name = "BusStopNumber",description = "버스정류장번호", example = "116000007")
    public List<RideBell> RideBellByBusStop(@PathVariable("BusStopNumber") String busStopNumber) {
        return rideBellService.findRideBellByBusStop(busStopNumber);
    }

    @Operation(summary = "Id로 승차벨 삭제하기", description = "Id로 승차벨 삭제하기")
    @Parameter(name = "Id", description = "고유 id")
    @DeleteMapping("RideBell/{Id}")
    public void DeleteRideBell(@PathVariable("Id") Long id) {
        rideBellService.deleteById(id);
    }

    @Operation(summary = "승차벨 전체삭제", description = "승차벨 전체 삭제하기")
    @DeleteMapping("RideBell")
    public void DeleteAllRideBell() {
        rideBellService.deleteAllRideBell();
    }

    @Operation(summary = "버스번호로 승차벨 삭제", description = "버스번호로 승차벨 삭제하기")
    @DeleteMapping("RideBell/BusNum/{BusNumber}")
    public void DeleteAllRideBellByBusNumber(@PathVariable("BusNumber") String busNumber) {
        rideBellService.deleteAllByBusNumber(busNumber);
    }




}
