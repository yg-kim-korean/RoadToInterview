package com.server.RoadToInerview.repository;

import com.server.RoadToInerview.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByEmail(String email);

    Users findByEmailAndPassword(String email, String password);
}
