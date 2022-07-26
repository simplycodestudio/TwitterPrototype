package org.lisiecki.hsbcchallenge.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lisiecki.hsbcchallenge.entities.Tweet;
import org.lisiecki.hsbcchallenge.exceptions.ApplicationException;
import org.lisiecki.hsbcchallenge.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
class TimelineController {

    private final UserService userService;

    @GetMapping("/{nickName}/timeline")
    @ResponseStatus(HttpStatus.OK)
    public List<Tweet> getTimeline(@PathVariable("nickName") final String nickName) throws ApplicationException {
        log.debug("Getting timeline for user {}",nickName);
        return userService.findTweetForFollowingUsersInReverseOrder(nickName);
    }
}