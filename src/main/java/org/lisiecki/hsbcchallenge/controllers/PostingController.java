package org.lisiecki.hsbcchallenge.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lisiecki.hsbcchallenge.exceptions.ApplicationException;
import org.lisiecki.hsbcchallenge.requests.TweetMessage;
import org.lisiecki.hsbcchallenge.services.TweetService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
class PostingController {

    private final TweetService tweetService;

    @PostMapping("/{nickName}/publish")
    @ResponseStatus(HttpStatus.OK)
    public long publish(@PathVariable String nickName, @RequestBody TweetMessage tweetMessage) throws ApplicationException {
        log.debug("publishing message by user {}", nickName);
        return tweetService.postTweet(nickName,tweetMessage.getMessage());
    }}
