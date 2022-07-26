package org.lisiecki.hsbcchallenge.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Tweet implements Comparable<Tweet>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    public AppUser user;
    private String message;
    @CreatedDate
    private LocalDateTime createdOn = LocalDateTime.now();

    public Tweet(AppUser user, String text) {
        this.user = user;
        this.message = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return Objects.equals(id, tweet.id) && Objects.equals(user, tweet.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, id);
    }

    @Override
    public int compareTo(Tweet anotherTweet) {
        return createdOn.compareTo(anotherTweet.getCreatedOn());
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "user=" + user +
                ", id=" + id +
                ", text='" + message + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}