package com.ASTRuleEngine.AstRuleEngine.controller;

import com.ASTRuleEngine.AstRuleEngine.service.RuleEngineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/api/rule-engine")
@Slf4j
public class RuleEngineController {

    private final RuleEngineService ruleEngineService;

    public RuleEngineController(RuleEngineService ruleEngineService){
        this.ruleEngineService = ruleEngineService;
    }

    @PostMapping("/create-rule")
    public String createRule(@RequestBody String rule){
        try {
            ruleEngineService.createRule(rule);
            return "Rule Created";
        } catch (Exception e) {
            log.error("Error while creating rule: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/combine-rules")
    public String combineRules(@RequestBody List<String> rules){
        try {
            ruleEngineService.combineRules(rules);
            return "Rules Combined";
        } catch (Exception e) {
            log.error("Error while combining rules: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
