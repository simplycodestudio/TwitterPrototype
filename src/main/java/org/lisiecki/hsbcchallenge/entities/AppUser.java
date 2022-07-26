package org.lisiecki.hsbcchallenge.entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nickName;
    private String realName;
    @CreatedDate
    private LocalDateTime registrationTimestamp = LocalDateTime.now();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH, mappedBy = "user")
    private Set<Tweet> tweets = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_FOLLOWERS", joinColumns = {
            @JoinColumn(name = "user_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "followed_id")
            }
    )
    private Set<AppUser> followers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "FOLLOWED_BY",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            }, inverseJoinColumns = {
            @JoinColumn(name = "follower_id")
    }
    )
    private Set<AppUser> following = new HashSet<>();

    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    public void addFollower(AppUser follower) {
        followers.add(follower);
    }

    public void follow(AppUser toFollow) {
        following.add(toFollow);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser user = (AppUser) o;
        return Objects.equals(id, user.id) && Objects.equals(nickName, user.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickName);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", realName='" + realName + '\'' +
                ", registrationTimestamp=" + registrationTimestamp +
                ", tweets=" + tweets +
                ", followers=" + followers +
                ", following=" + following +
                '}';
    }
}