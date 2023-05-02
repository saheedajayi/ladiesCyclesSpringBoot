package com.paragons.ladiescycles.data.repositories;

import com.paragons.ladiescycles.data.models.Lady;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LadyRepository extends JpaRepository<Lady, Long> {
    Optional<Lady> findByUsername(String name);
    Boolean existsByUsername(String name);
}
