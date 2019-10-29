package dy.kabang.s1pkafkarentcardemo.domain;

public class CarEvent {

    private String uuid;
    private double latidude;
    private double longitude;
    private double speed;

    public CarEvent(String uuid, Double latidude, Double longitude, Double speed) {
        this.uuid = uuid;
        this.latidude = latidude;
        this.longitude = longitude;
        this.speed = speed;
    }
    public String getUuid() {
        return uuid;
    }

    public double getLatidude() {
        return latidude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getSpeed() {
        return speed;
    }
}
