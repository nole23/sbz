package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.model.ProfileUser;

public interface ProfileUserRepository extends JpaRepository<ProfileUser, Long> {

}
