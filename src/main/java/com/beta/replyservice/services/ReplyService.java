package com.beta.replyservice.services;

import com.beta.replyservice.dto.response.ReplyDto;

public interface ReplyService {
    ReplyDto getReplyV2(String inputMessage);
}
