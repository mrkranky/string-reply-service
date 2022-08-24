package com.beta.replyservice.dto.response;

public class ReplyDto {
    private final String data;

    public ReplyDto(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }
}
