package dy.kabang.s1pkafkarentcardemo.stream;

import dy.kabang.s1pkafkarentcardemo.bindings.ViolationCheckBinder;
import dy.kabang.s1pkafkarentcardemo.domain.ViolationEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableBinding(ViolationCheckBinder.class)
public class ViolationStream {
    @StreamListener(ViolationCheckBinder.VIOLATION_IN)
    public void processViolation(KStream<String, ViolationEvent> violations) {
        violations.foreach((k, v) -> log.info("[[Violation Check]] ID: {}, Value: {}", k, v.toString()));
    }
}