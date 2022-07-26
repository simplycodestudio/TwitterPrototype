package org.lisiecki.hsbcchallenge.resources;

import org.lisiecki.hsbcchallenge.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser,Long> {

    Optional<AppUser> findByNickName(String nickName);
    boolean existsByNickName(String nickName);
}
