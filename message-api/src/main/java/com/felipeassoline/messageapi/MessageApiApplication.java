package com.felipeassoline.messageapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class MessageApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageApiApplication.class, args);
    }


    @EnableBinding({Source.class})
    @RestController
    class MessageController {

        private final Logger logger = LoggerFactory.getLogger(MessageController.class);
        private Source source;

        MessageController(Source source) {
            this.source = source;
        }

        @GetMapping("/send")
        public String send(@RequestParam String payload) {
            logger.info(payload);
            source.output().send(MessageBuilder.withPayload(payload).build());
            return payload;
        }
    }
}
