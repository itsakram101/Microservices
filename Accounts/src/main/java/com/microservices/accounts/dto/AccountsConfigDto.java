package com.microservices.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
@RefreshScope
public class AccountsConfigDto {

    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;

}
