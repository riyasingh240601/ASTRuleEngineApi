package com.ASTRuleEngine.AstRuleEngine.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Data
@Slf4j
@Builder
@Table(name = "rule_engine")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RuleEngineModel {
    @Id
    @JsonProperty("Id")
    @GeneratedValue
    private int id;

    @NonNull
    @JsonProperty("rule")
    private String rule;

    @JsonProperty("created_at")
    private String createdAt;

    public RuleEngineModel(String rule) {
        this.rule = rule;
        this.createdAt = Instant.now().toString();
    }
}
