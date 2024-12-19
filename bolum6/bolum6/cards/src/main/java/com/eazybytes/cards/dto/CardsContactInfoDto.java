package com.eazybytes.cards.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.List;
import java.util.Map;


@ConfigurationProperties(prefix="cards")
public record CardsContactInfoDto(String message, Map<String, String> contactDetails, List<String> onCallSupport ) {
}
