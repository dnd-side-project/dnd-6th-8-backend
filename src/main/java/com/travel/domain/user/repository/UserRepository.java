package com.travel.domain.user.repository;

import com.travel.domain.archive.entity.Archives;
import com.travel.domain.user.entity.Survey;
import com.travel.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(@Param(value="email")String email);
    User findByUserName(String userName);
}
