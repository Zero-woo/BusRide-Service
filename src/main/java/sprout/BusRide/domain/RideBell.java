package sprout.BusRide.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "ridebell")
@NoArgsConstructor
@AllArgsConstructor
public class RideBell {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String busStopId;

    private String message;

    @Builder
    public RideBell(String name, String busStopId, String message) {
        this.name = name;
        this.busStopId = busStopId;
        this.message = message;
    }

}
