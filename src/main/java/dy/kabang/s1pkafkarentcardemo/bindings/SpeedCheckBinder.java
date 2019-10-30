package dy.kabang.s1pkafkarentcardemo.bindings;

import dy.kabang.s1pkafkarentcardemo.domain.CarEvent;
import dy.kabang.s1pkafkarentcardemo.domain.ViolationEvent;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

public interface SpeedCheckBinder {
    String SPEED_CHECK_IN = "speed-checks-in";
    String VIOLATION_OUT = "violations-out";

    @Input(SPEED_CHECK_IN)
    KStream<String, CarEvent> speedCheckInbound();

    @Output(VIOLATION_OUT)
    KStream<String, ViolationEvent> violoationOutbound();
}
