package com.felipeassoline.messageprocessor;

import com.felipeassoline.messagesdk.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@SpringBootApplication
public class MessageProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageProcessorApplication.class, args);
    }

    @EnableBinding(Sink.class)
    class MessageProcessor {

        private final Logger logger = LoggerFactory.getLogger(MessageProcessor.class);

        @StreamListener(target = Sink.INPUT)
        public void process(Message<MessageEvent> message) {
            logger.info("Processing payload = " + message);
        }

    }
}
