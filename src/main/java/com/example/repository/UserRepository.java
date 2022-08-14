package com.example.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Component;

import com.example.entities.User;

@Component
public interface UserRepository extends JpaRepositoryImplementation<User, Long>{

}
