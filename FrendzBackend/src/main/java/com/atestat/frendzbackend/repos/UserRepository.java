package com.atestat.frendzbackend.repos;

import com.atestat.frendzbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Modifying
    @Query("UPDATE User u SET u.facebookLink = :facebookLink WHERE u.id = :id")
    void updateFacebookLink(@Param(value = "id") Long Id, @Param(value = "facebookLink") String facebookLink);

    @Modifying
    @Query("UPDATE User u SET u.instagramLink = :instagramLink WHERE u.id = :id")
    void updateInstagramLink(@Param(value = "id") Long Id, @Param(value = "instagramLink") String instagramLink);
}
