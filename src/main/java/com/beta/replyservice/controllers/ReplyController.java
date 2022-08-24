package com.beta.replyservice.controllers;

import com.beta.replyservice.dto.response.ReplyDto;
import com.beta.replyservice.exceptions.InvalidInputMessageException;
import com.beta.replyservice.services.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReplyController {

	private final ReplyService replyService;

	@Autowired
	public ReplyController(ReplyService replyService) {
		this.replyService = replyService;
	}

	@GetMapping("/reply")
	public ReplyDto replying() {

		// throw exception to return HTTP.BAD_REQUEST(400) with error message
		throw new InvalidInputMessageException("Message is empty");
	}

	@GetMapping("/reply/{message}")
	public ReplyDto replying(@PathVariable String message) {
		return new ReplyDto(message);
	}

	@GetMapping("/v2/reply/{message}")
	public ReplyDto replyingV2(@PathVariable String message) {
		return replyService.getReplyV2(message);
	}
}