package sprout.BusRide.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sprout.BusRide.domain.RideBell;
import sprout.BusRide.repository.RideBellRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RideBellService {

    private final RideBellRepository rideBellRepository;

    public void saveRideBell(RideBell rideBell) {
        rideBellRepository.save(rideBell);
    }

    public List<RideBell> findRideBells() {
        return rideBellRepository.findAll();
    }

}
