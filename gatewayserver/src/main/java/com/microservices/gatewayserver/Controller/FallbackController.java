package com.microservices.gatewayserver.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {


    @RequestMapping("/contactSupport")
    public Mono<String> contactSupport(){

        return Mono.just
                ("An error occurred, Try after 15 seconds or send email to our support team at: company@gmail.com");
    }
}
