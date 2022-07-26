package org.lisiecki.hsbcchallenge.resources;

import org.lisiecki.hsbcchallenge.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
