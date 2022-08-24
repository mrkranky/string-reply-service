package com.beta.replyservice.rules;

import org.springframework.stereotype.Component;

@Component
public class ReverseRule implements Rule {
    public static final int OPERATION_ID = 1;

    @Override
    public String applyRule(String input) {
        if (input == null) {
            return null;
        }

        return new StringBuilder(input).reverse().toString();
    }
}
