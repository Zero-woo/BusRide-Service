package sprout.BusRide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sprout.BusRide.domain.RideBell;

import java.util.List;

@Repository
public interface RideBellRepository extends JpaRepository<RideBell, Long> {

    <S extends RideBell> S save(S entity);

    List<RideBell> findAll();
}
