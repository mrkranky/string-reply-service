package com.beta.replyservice.rules;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReverseRuleTest {

    @Autowired
    ReverseRule reverseRule;

    @Test
    public void givenNullString_whenReverseRuleIsApplied_thenReturnNull() {
        assertThat(reverseRule.applyRule(null)).isNull();
    }

    @Test
    public void givenString_whenReverseRuleIsApplied_thenReturnReversedString() {
        assertThat(reverseRule.applyRule("kbzw9ru")).isEqualTo("ur9wzbk");
    }
}
