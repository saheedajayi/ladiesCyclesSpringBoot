package com.paragons.ladiescycles.data.repositories;

import com.paragons.ladiescycles.data.models.Cycles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CyclesRepository extends JpaRepository<Cycles, Long> {
}
