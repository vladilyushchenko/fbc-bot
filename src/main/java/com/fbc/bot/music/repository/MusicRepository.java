package com.fbc.bot.music.repository;

import com.fbc.bot.music.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MusicRepository extends JpaRepository<Music, Long> {

    Optional<Music> findByTitleIgnoreCaseAndAuthorIgnoreCase(String title, String author);

    Optional<Music> findByLinkIgnoreCase(String link);
}