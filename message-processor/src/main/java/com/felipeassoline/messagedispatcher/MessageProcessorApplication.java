package com.felipeassoline.messagedispatcher;

import com.felipeassoline.schemas.MsgWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootApplication
@EnableSchemaRegistryClient
public class MessageProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageProcessorApplication.class, args);
    }

    @EnableBinding(Processor.class)
    class MessageProcessor {

        private final Processor processor;
        private final Logger logger = LoggerFactory.getLogger(MessageProcessor.class);

        MessageProcessor(Processor processor) {
            this.processor = processor;
        }

        @StreamListener(target = Sink.INPUT)
        public void process(MsgWrapper message) {
            logger.info("Processing payload = " + message);
            processor.output().send(MessageBuilder.withPayload(message).build());
        }

    }
}
