package sprout.BusRide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sprout.BusRide.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    <S extends Member> S save(S entity);

    Optional<Member> findByEmail(String email);

    Optional<Member> findById(String id);

}
