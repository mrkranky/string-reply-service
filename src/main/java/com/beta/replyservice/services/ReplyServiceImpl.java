package com.beta.replyservice.services;

import com.beta.replyservice.dto.response.ReplyDto;
import com.beta.replyservice.exceptions.InvalidInputMessageException;
import com.beta.replyservice.exceptions.OperationNotFoundException;
import com.beta.replyservice.rules.RulesRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ReplyServiceImpl implements ReplyService {

    private final RulesRegistry rulesRegistry;

    private static final String MESSAGE_REGEX = "^\\d{2}-[a-z0-9]+$";

    @Autowired
    public ReplyServiceImpl(RulesRegistry rulesRegistry) {
        this.rulesRegistry = rulesRegistry;
    }

    @Override
    public ReplyDto getReplyV2(String inputMessage) {
        // check if the string is valid or not
        if (!validate(inputMessage)) {
            throw new InvalidInputMessageException("Invalid input");
        }

        // split the string by dash (-)
        String[] split = inputMessage.split("-");

        String operations = split[0];
        String str = split[1];

        for (int i=0; i<operations.length(); i++) {
            int operation = operations.charAt(i) - '0';

            if (!isOperationFound(operation)) {
                throw new OperationNotFoundException("Operation not found: " + operation);
            }

            str = rulesRegistry.getRule(operation).applyRule(str);
        }

        return new ReplyDto(str);
    }

    private boolean validate(String message) {
        return Pattern.matches(MESSAGE_REGEX, message);
    }

    private boolean isOperationFound(int operation) {
        return rulesRegistry.getRule(operation) != null;
    }
}
