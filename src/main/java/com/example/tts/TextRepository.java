package com.example.tts;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TextRepository extends JpaRepository<TextEntity, Long> {}
