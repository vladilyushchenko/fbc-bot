package com.fbc.bot.repository;

import com.fbc.bot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByTelegramId(Integer telegramId);
}