package org.lisiecki.hsbcchallenge.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.lisiecki.hsbcchallenge.entities.AppUser;
import org.lisiecki.hsbcchallenge.entities.Tweet;
import org.lisiecki.hsbcchallenge.exceptions.ApplicationException;
import org.lisiecki.hsbcchallenge.resources.TweetRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
public class TweetService {

    private final TweetRepository tweetRepository;
    private final UserService userService;
    private final Integer MAX_MESSAGE_LENGTH = 140;

    @Transactional
    public long postTweet(String nickName, String message) throws ApplicationException {
        AppUser user = getUser(nickName);
        handleExceptions(message);
        Tweet tweet = new Tweet(user, message);
        return tweetRepository.save(tweet).getId();
    }

    private AppUser getUser(String nickName) throws ApplicationException {
        AppUser user = null;
        if (!userService.isUserExists(nickName)) {
            user = userService.createUser(nickName);
        }
        return user == null ? userService.findUser(nickName) : user;
    }

    private void handleExceptions(String message) throws ApplicationException {
        if (StringUtils.isBlank(message)) {
            throw new ApplicationException("User's tweet message is empty.");
        }
        if (message.length() > MAX_MESSAGE_LENGTH) {
            throw new ApplicationException(String.format("%d characters exceeded.", MAX_MESSAGE_LENGTH));
        }
    }
}