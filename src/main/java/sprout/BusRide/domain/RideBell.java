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

    private String busStopName;

    private String passengerType;

    private String seatType;

    private String message;

    @Builder
    public RideBell(String busNumber, String busStopName, String passengerType,String seatType, String message) {
        this.busNumber = busNumber;
        this.busStopName = busStopName;
        this.passengerType = passengerType;
        this.seatType = seatType;
        this.message = message;
    }

}
