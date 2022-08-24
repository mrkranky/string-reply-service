package com.beta.replyservice.rules;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class RulesRegistry {
    private final Map<Integer, Rule> ruleMap;

    private final ReverseRule reverseRule;
    private final MD5HashRule md5HashRule;

    @Autowired
    public RulesRegistry(ReverseRule reverseRule, MD5HashRule md5HashRule) {
        this.reverseRule = reverseRule;
        this.md5HashRule = md5HashRule;

        this.ruleMap = new HashMap<>();
    }

    @PostConstruct
    public void postConstruct() {
        registerRules();
    }

    public void registerRules() {
        ruleMap.put(ReverseRule.OPERATION_ID, reverseRule);
        ruleMap.put(MD5HashRule.OPERATION_ID, md5HashRule);
    }

    public Rule getRule(int ruleNum) {
        return ruleMap.getOrDefault(ruleNum, null);
    }
}
