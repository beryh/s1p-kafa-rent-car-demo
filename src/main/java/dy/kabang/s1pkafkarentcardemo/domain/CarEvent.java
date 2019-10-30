package dy.kabang.s1pkafkarentcardemo.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CarEvent {
    private String uuid;
    private double latitude;
    private double longitude;
    private double speed;
}
