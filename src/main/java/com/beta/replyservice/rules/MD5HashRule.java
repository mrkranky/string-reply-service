package com.beta.replyservice.rules;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Component
public class MD5HashRule implements Rule {
    public static final int OPERATION_ID = 2;

    @Override
    public String applyRule(String input) {
        if (input == null) {
            return null;
        }

        return DigestUtils.md5DigestAsHex(input.getBytes(StandardCharsets.UTF_8));
    }
}
