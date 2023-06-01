package sprout.BusRide.controller;

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

@RestController
@RequiredArgsConstructor
public class BusStopController {

    private final BusStopService busStopService;

    @PostMapping("/busStop")
    public ResponseEntity<String> searchBusStop(@RequestBody BusStopName busStopName) throws IOException {
        String name = busStopName.getName();
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(busStopService.getStationByNameList(name));
    }


    @Data
    static class BusStopName{
        private String name;
    }
}
