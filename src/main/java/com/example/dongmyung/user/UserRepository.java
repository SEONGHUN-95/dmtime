package com.example.dongmyung.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
    Optional<SiteUser> findByusername(String username);
    List<SiteUser> findAllByIsMemberTrue();
    List<SiteUser> findAllByIsMemberFalse();

}
