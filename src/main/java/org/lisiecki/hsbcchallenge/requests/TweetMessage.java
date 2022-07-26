package org.lisiecki.hsbcchallenge.requests;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class TweetMessage {

    @NonNull
    private String message;

    private final LocalDateTime creationTime;

    public TweetMessage() {
        creationTime = LocalDateTime.now();
    }
}
