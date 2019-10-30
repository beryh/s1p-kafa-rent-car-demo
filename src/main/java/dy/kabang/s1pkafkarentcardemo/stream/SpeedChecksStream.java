package dy.kabang.s1pkafkarentcardemo.stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import dy.kabang.s1pkafkarentcardemo.bindings.SpeedCheckBinder;
import dy.kabang.s1pkafkarentcardemo.domain.CarEvent;
import dy.kabang.s1pkafkarentcardemo.domain.ViolationEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.state.WindowStore;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Slf4j
@Component
@EnableBinding(SpeedCheckBinder.class)
public class SpeedChecksStream {
    private final String WINDOW_STORE = "violations-store";
    private final int WINDOW_SIZE_MS = 30000;

    @StreamListener(SpeedCheckBinder.SPEED_CHECK_IN)
    @SendTo(SpeedCheckBinder.VIOLATION_OUT)
    public KStream<String, ViolationEvent> speedCheck(KStream<String, CarEvent> carEvents) {
        carEvents.foreach((k, v) -> log.info("[[CarEvent]] key: {}, value: {}", k, v.toString()));

        ObjectMapper violationEventMapper = new ObjectMapper();
        Serde<ViolationEvent> violationEventSerde = new JsonSerde<>(ViolationEvent.class, violationEventMapper);

        KStream<String, ViolationEvent> violations = carEvents
                .groupByKey()
                .windowedBy(TimeWindows.of(Duration.ofMillis(WINDOW_SIZE_MS)))
                .aggregate(
                        ViolationEvent::new,
                        (k, carEvent, violationEvent) -> violationEvent.addCarEvent(carEvent),
                        Materialized.<String, ViolationEvent, WindowStore<Bytes, byte[]>>as(WINDOW_STORE)
                .withKeySerde(Serdes.String())
                .withValueSerde(violationEventSerde))
                .mapValues(ViolationEvent::closeWindow)
                .toStream()
                .filter((k, v) -> v.getViolationCount() > 2)
                .selectKey((k, v) -> k.key());

        violations.foreach((k, v) -> log.info("VIOLATION key = {}, value = {}", k, v.toString()));
        return violations;
    }
}
