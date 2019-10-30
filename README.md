## Spring Cloud Stream & Kafka를 통한 Event-Driven Demo

Kafka와 Spring Cloud Stream을 이용하여, 실시간으로 렌트카의 위치와 속도정보를 통해 과속 운전 정보를 수집하는 데모입니다.  


### Requirement
Docker (for Kafka)
Java 8 (Spring boot 2.2.0)

### How to run
```
> docker-compose -f ./kafka-docker/zk-single-kafka-single.yml up
> gradle clean bootRun
```

### Roles & Responsbility

### Reference
https://springoneplatform.io/2019/sessions/rabbitmq-kafka
https://www.youtube.com/watch?v=7Faly8jORIw&t=2282s
 > Kafka docker-compose
 > https://github.com/simplesteph/kafka-stack-docker-compose

