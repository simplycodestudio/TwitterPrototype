package org.lisiecki.hsbcchallenge.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lisiecki.hsbcchallenge.exceptions.ApplicationException;
import org.lisiecki.hsbcchallenge.requests.FollowRequest;
import org.lisiecki.hsbcchallenge.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/follow")
class FollowingController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void follow(@RequestBody FollowRequest followRequest) throws ApplicationException {
        userService.follow(followRequest);
        log.debug("User {} has started following user {}", followRequest.getFollower(),followRequest.getFollowed());
    }
}