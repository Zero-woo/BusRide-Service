package sprout.BusRide.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sprout.BusRide.service.BusStopService;

import java.io.IOException;

@Tag(name = "BusStop", description = "버스정류장에 관한 정보")
@RestController
@RequiredArgsConstructor
public class BusStopController {

    private final BusStopService busStopService;

    @Operation(summary = "버스 정류장 검색", description = "정류장 이름을 검색하여 버스 정류장 정보를 가져온다.")
    @Parameter(name = "name", description = "정류장 이름", example = "신도림")
    @PostMapping("/busStop")
    public ResponseEntity<String> searchBusStop(@RequestBody BusStopName busStopName) throws IOException {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(busStopService.getStationByNameList(busStopName.getName()));
    }

    @Operation(summary = "주변 정류장 검색", description = "위치 기반 주변 정류장 위치를 가져온다.")
    @Parameters({
            @Parameter(name = "tmX", description = "현재 x좌표", example = "126.8813245848"),
            @Parameter(name = "tmY", description = "현재 y좌표", example = "37.5073421413"),
            @Parameter(name = "radius", description = "반경 m(최대 2000)", example = "300")

    })
    @PostMapping("/busStopByPos")
    public ResponseEntity<String> searchNearBusStop(@RequestBody BusStopPosAndName busStopPosAndName) throws IOException {
        String tmX = busStopPosAndName.tmX;
        String tmY = busStopPosAndName.tmY;
        String radius = busStopPosAndName.radius;

        ResponseEntity<String> body = ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(busStopService.getStationByPosList(tmX, tmY, radius));
        return body;
    }

    @Operation(summary = "정류장 도착 정보", description = "정류장 도착 버스 정보를 확인한다")
    @Parameter(name ="busStopInfo", description = "정류장ID", example = "17003")
    @PostMapping("busStopInfo")
    public ResponseEntity<String> searchBusStopInfo(@RequestBody BusStopId busStopId) throws IOException{
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(busStopService.getStationInfo(busStopId.getBusStopId()));
    }


    @Data
    static class BusStopName {
        private String name;
    }

    @Data
    static class BusStopId{
        private String busStopId;
    }

    @Data
    static class BusStopPosAndName{
        private String tmX;
        private String tmY;
        private String radius;
    }
}
