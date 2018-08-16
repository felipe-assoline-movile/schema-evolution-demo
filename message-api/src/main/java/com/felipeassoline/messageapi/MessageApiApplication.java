package com.felipeassoline.messageapi;

import com.felipeassoline.messagesdk.MessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableSchemaRegistryClient
@EnableBinding({Source.class})
public class MessageApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageApiApplication.class, args);
    }

    @RestController
    class MessageController {
        private final Source source;
        private final Logger logger = LoggerFactory.getLogger(MessageController.class);

        @Autowired
        MessageController(final Source source) {
            this.source = source;
        }

        @GetMapping("/send")
        public MessageEvent send(final String messageText, final String messageTo) {
            MessageEvent messageEvent = new MessageEvent(messageTo, messageText);
            logger.info(messageText);
            source.output().send(MessageBuilder.withPayload(messageEvent).build());
            return messageEvent;
        }
    }
}
