package com.beta.replyservice.rules;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MD5HashRuleTest {

    @Autowired
    MD5HashRule md5HashRule;

    @Test
    public void givenNullString_whenMD5HashRuleIsApplied_thenReturnNull() {
        assertThat(md5HashRule.applyRule(null)).isNull();
    }

    @Test
    public void givenString_whenMD5HashRuleIsApplied_thenReturnReversedString() {
        assertThat(md5HashRule.applyRule("kbzw9ru")).isEqualTo("0fafeaae780954464c1b29f765861fad");
    }
}

