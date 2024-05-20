package com.microservices.loanms.Dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;


@ConfigurationProperties(prefix = "loans")
@Getter
@Setter
public class LoansConfigDto {

    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;
}
