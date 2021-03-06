package com.felipeassoline.messagedispatcher;

import com.felipeassoline.schemas.MsgWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;
import org.springframework.messaging.Message;

@SpringBootApplication
@EnableSchemaRegistryClient
public class MessageDispatcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageDispatcherApplication.class, args);
    }

    @EnableBinding(Sink.class)
    class MessageProcessor {

        private final Logger logger = LoggerFactory.getLogger(MessageProcessor.class);

        @StreamListener(target = Sink.INPUT)
        public void process(Message<MsgWrapper> message) {
            logger.info("Processing payload = " + message.getPayload());
        }

    }
}
