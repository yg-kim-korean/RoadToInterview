package com.server.RoadToInerview.repository;

import com.server.RoadToInerview.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByEmail(String email);

    @Override
    Optional<Users> findById(Long aLong);

    Users findUsersByEmailAndPassword(String email, String password);

    Users findByNickname(String nickname);
}
