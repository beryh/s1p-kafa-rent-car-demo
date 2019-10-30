package dy.kabang.s1pkafkarentcardemo.bindings;

import dy.kabang.s1pkafkarentcardemo.domain.ViolationEvent;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;

public interface ViolationCheckBinder {
    String VIOLATION_IN = "violations-in";

    @Input(VIOLATION_IN)
    KStream<String, ViolationEvent> violationsInbound();
}
