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

    private String busNumber;

    private String busStopNumber;

    private String passengerType;

    private String message;

    @Builder
    public RideBell(String busNumber, String busStopNumber, String passengerType,String message) {
        this.busNumber = busNumber;
        this.busStopNumber = busStopNumber;
        this.passengerType = passengerType;
        this.message = message;
    }

}
