package dy.kabang.s1pkafkarentcardemo.bindings;

import dy.kabang.s1pkafkarentcardemo.domain.ViolationEvent;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;

public interface ViolationsCheckBinding {
    String VIOLATION_IN = "violations-in";
    String VIOLATION_OUT = "violations-out";

    @Input(VIOLATION_IN)
    KStream<String, ViolationEvent> violationsIn();

    @Output(VIOLATION_OUT)
    KStream<String, ViolationEvent> violationsOut();
}
