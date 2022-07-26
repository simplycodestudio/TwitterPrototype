package org.lisiecki.hsbcchallenge.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
public class FollowRequest {

    @NonNull
    private String follower;
    @NonNull
    private String followed;
}
