//package dy.kabang.s1pkafkarentcardemo.streams;
//
//import dy.kabang.s1pkafkarentcardemo.bindings.ViolationsCheckBinding;
//import dy.kabang.s1pkafkarentcardemo.domain.ViolationEvent;
//import org.apache.kafka.streams.kstream.KStream;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.stream.annotation.EnableBinding;
//import org.springframework.cloud.stream.annotation.StreamListener;
//import org.springframework.stereotype.Component;
//
////@Component
////@EnableBinding(ViolationsCheckBinding.class)
////public class ViolationStream {
////    Logger log = LoggerFactory.getLogger(getClass());
////
////    @StreamListener(ViolationsCheckBinding.VIOLATION_IN)
////    public void processViolations(KStream<String, ViolationEvent> violations) {
////        violations.foreach((k, v) -> log.info("ViolationCheck uuid = " + k + ", " + v.toString()));
////    }
////}