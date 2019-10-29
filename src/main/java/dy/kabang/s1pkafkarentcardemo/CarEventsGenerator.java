package dy.kabang.s1pkafkarentcardemo;

import dy.kabang.s1pkafkarentcardemo.domain.CarEvent;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CarEventsGenerator {

    @Component
    @EnableBinding(CarEventsSource.class)
    public static class CarEventsProducer implements ApplicationRunner {

        private final MessageChannel carEventsOut;

        public CarEventsProducer(CarEventsSource binding) {
            carEventsOut = binding.carEventsOut();
        }

        @Override
        public void run(ApplicationArguments args) throws Exception {
            //14
            Double[] latidudes = new Double[] {40.7322158, 40.752185, 40.65185, 40.95185, 40.99185, 40.9988, 40.7433066, 40.7430, 40.743258, 40.7111, 40.85185, 40.66585, 40.39115, 40.4188, 40.443066};

            //14
            Double[] longitudes = new Double[] {-73.992602, -73.892702, -73.7392702, -73.957021, -74.03237522, -74.12375, -73.24577, -74.056995, -74.05621, -74.1212, -74.56512, -74.7271};

            //14
            Double[] speeds = new Double[] {76D, 75D, 72D, 40D, 25D, 80D, 74D, 78D, 60D, 71D, 62D, 76D, 65D, 82D};

            //4
            String[] uuids = new String[] {"1", "2", "3", "4"};

            Random random = new Random();

            Runnable runnable = () -> {
                int laIdx, lnIdx, sIdx, uIdx;
                laIdx = random.nextInt(14);
                lnIdx = random.nextInt(14);
                sIdx = random.nextInt(14);
                uIdx = random.nextInt(4);
                CarEvent carEvent = new CarEvent(uuids[uIdx], latidudes[laIdx], longitudes[lnIdx], speeds[sIdx]);

                System.out.println(carEvent.toString());
                Message<?> message = MessageBuilder.withPayload(carEvent)
                        .setHeader(KafkaHeaders.MESSAGE_KEY, carEvent.getUuid().getBytes()).build();

                try {
                    carEventsOut.send(message);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            };

            Executors.newScheduledThreadPool(1).scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
        }
    }
}

interface CarEventsSource {
    String CAR_EVENTS_OUT = "car-events-out";

    @Output(CarEventsSource.CAR_EVENTS_OUT)
    MessageChannel carEventsOut();
}