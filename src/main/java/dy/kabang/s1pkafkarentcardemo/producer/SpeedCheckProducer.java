package dy.kabang.s1pkafkarentcardemo.producer;

import dy.kabang.s1pkafkarentcardemo.domain.CarEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@EnableBinding(CarEventsSource.class)
public class SpeedCheckProducer implements ApplicationRunner {
    private final MessageChannel carEventOut;

    private List<Double> latitudes = Arrays.asList(40.7322158, 40.752185, 40.65185, 40.95185, 40.99185, 40.9988, 40.7433066, 40.7430, 40.743258, 40.7111, 40.85185, 40.66585, 40.39115, 40.4188, 40.443066);
    private List<Double> longitudes = Arrays.asList(-73.992602, -73.892702, -73.7392702, -73.957021, -74.03237522, -74.12375, -73.24577, -74.056995, -74.05621, -74.1212, -74.56512, -74.7271);
    private List<Double> speeds = Arrays.asList(76D, 75D, 72D, 40D, 25D, 80D, 74D, 78D, 60D, 71D, 62D, 76D, 65D, 82D);
    private List<String> uuids = Arrays.asList("1", "2", "3", "4");

    public SpeedCheckProducer(CarEventsSource carEventsSource) {
        this.carEventOut = carEventsSource.carEventOutChannel();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Random random = new Random();
        Runnable runnable = () -> {
            int laIdx, lnIdx, sIdx, uIdx;
            laIdx = random.nextInt(latitudes.size() - 1);
            lnIdx = random.nextInt(longitudes.size() - 1);
            sIdx = random.nextInt(speeds.size() - 1);
            uIdx = random.nextInt(uuids.size() - 1);

            CarEvent carEvent = new CarEvent(uuids.get(uIdx), latitudes.get(laIdx), longitudes.get(lnIdx), speeds.get(sIdx));

            Message<?> message = MessageBuilder.withPayload(carEvent)
                    .setHeader(KafkaHeaders.MESSAGE_KEY, carEvent.getUuid().getBytes()).build();

            try {
                carEventOut.send(message);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        };

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
    }
}

interface CarEventsSource {
    String CAR_EVENTS_OUT = "speed-checks-out";

    @Output(CAR_EVENTS_OUT)
    MessageChannel carEventOutChannel();
}
