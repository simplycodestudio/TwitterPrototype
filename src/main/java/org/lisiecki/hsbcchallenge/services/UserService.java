package org.lisiecki.hsbcchallenge.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lisiecki.hsbcchallenge.entities.AppUser;
import org.lisiecki.hsbcchallenge.entities.Tweet;
import org.lisiecki.hsbcchallenge.exceptions.ApplicationException;
import org.lisiecki.hsbcchallenge.requests.FollowRequest;
import org.lisiecki.hsbcchallenge.resources.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public AppUser createUser(final String nickName) throws ApplicationException {
        if (userRepository.existsByNickName(nickName)) {
            throw new ApplicationException(String.format("User %s exists.", nickName));
        }
        AppUser user = new AppUser();
        user.setNickName(nickName);
        return userRepository.save(user);
    }

    public boolean isUserExists(final String nickName){
        return userRepository.existsByNickName(nickName);
    }

    public AppUser findUser(final String nickName) throws ApplicationException {
        return userRepository.findByNickName(nickName).orElseThrow(() -> new ApplicationException("User with given name not exists."));
    }

    @Transactional
    public void follow(final FollowRequest followRequest) throws ApplicationException {
        AppUser follower = findUser(followRequest.getFollower());
        AppUser followed = findUser(followRequest.getFollowed());

        followed.addFollower(follower);
        follower.follow(followed);
    }

    public List<Tweet> findTweetForFollowingUsersInReverseOrder(final String nickName) throws ApplicationException {
        return findUser(nickName).getFollowing().stream()
                .map(AppUser::getTweets)
                .flatMap(Collection::stream)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public List<Tweet> getUserWithTweetsInReverseOrder(String nickName) throws ApplicationException{
        return findUser(nickName).getTweets().stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}