package dy.kabang.s1pkafkarentcardemo.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ViolationEvent {
    private final static double SPEED_THRESHOLD = 60D;
    private String uuid;
    private List<CarEvent> carEvents;

    private long start;
    private long end;

    private int violationCount;

    public ViolationEvent() {
        carEvents = new ArrayList<>();
        start = new Date().getTime();
    }

    public ViolationEvent addCarEvent(CarEvent carEvent) {
        if (uuid == null)
            uuid = carEvent.getUuid();

        if (carEvent.getSpeed() > SPEED_THRESHOLD) {
            violationCount++;
            carEvents.add(carEvent);
        }

        return this;
    }

    public ViolationEvent closeWindow() {
        end = new Date().getTime();

        return this;
    }
}
