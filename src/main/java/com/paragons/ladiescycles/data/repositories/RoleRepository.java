package com.paragons.ladiescycles.data.repositories;

import com.paragons.ladiescycles.data.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
