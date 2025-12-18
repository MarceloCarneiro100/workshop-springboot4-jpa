package com.meuprojetoweb.course.repositories;

import com.meuprojetoweb.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
