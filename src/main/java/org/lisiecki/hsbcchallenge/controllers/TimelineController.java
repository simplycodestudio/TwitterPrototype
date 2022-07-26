package org.lisiecki.hsbcchallenge.controllers;

import lombok.RequiredArgsConstructor;
import org.lisiecki.hsbcchallenge.entities.Tweet;
import org.lisiecki.hsbcchallenge.exceptions.ApplicationException;
import org.lisiecki.hsbcchallenge.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping
class TimelineController {

    private final UserService userService;

    @GetMapping("/{nickName}/timeline")
    @ResponseStatus(HttpStatus.OK)
    public List<Tweet> getTimeline(@PathVariable("nickName") final String nickName) throws ApplicationException {
        return null;
    }
}