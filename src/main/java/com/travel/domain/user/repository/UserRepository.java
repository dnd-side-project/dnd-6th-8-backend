package com.travel.domain.user.repository;

import com.travel.domain.user.entity.Survey;
import com.travel.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
