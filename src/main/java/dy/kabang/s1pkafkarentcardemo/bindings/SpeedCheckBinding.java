package dy.kabang.s1pkafkarentcardemo.bindings;

import dy.kabang.s1pkafkarentcardemo.domain.CarEvent;
import dy.kabang.s1pkafkarentcardemo.domain.ViolationEvent;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

public interface SpeedCheckBinding {
    String CAR_EVENTS_IN = "car-events-in";
    String VIOLATIONS_OUT = "violations-out";

    @Input(CAR_EVENTS_IN)
    KStream<String, CarEvent> carEventsIn();

    @Output(VIOLATIONS_OUT)
    KStream<String, ViolationEvent> violationsOut();
}
