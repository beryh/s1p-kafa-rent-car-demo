package dy.kabang.s1pkafkarentcardemo.domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViolationEvent {
    private final double SPEED_THRESHOLD = 40D;

    private String uuid;
    private List<CarEvent> carEvents;
    private long start;
    private long end;

    private int violationCount;
    private double speed;

    public ViolationEvent() {
        carEvents = new ArrayList<>();
        start = new Date().getTime();
    }

    public String getUuid() {
        return uuid;
    }

    public List<CarEvent> getCarEvents() {
        return carEvents;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public double getSpeed() {
        return this.speed;
    }

    public int getViolationCount() {
        return violationCount;
    }

    public ViolationEvent addCarEvent(CarEvent cpe) {
        if (uuid == null)
            uuid = cpe.getUuid();

        if (cpe.getSpeed() > SPEED_THRESHOLD) {
            violationCount++;
            carEvents.add(cpe);

        }
        return this;
    }

    public ViolationEvent closeWindow() {
        end = new Date().getTime();
        return this;
    }

    @Override
    public String toString() {
        return "ViolationEvent{" +
                "uuid='" + uuid + "\'" +
                ", count=" + violationCount +
                ", start=" + new Date(start) +
                " end=" + new Date(end);
    }
}

