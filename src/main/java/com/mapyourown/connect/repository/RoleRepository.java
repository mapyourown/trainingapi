package com.mapyourown.connect.repository;

import java.util.Optional;

import com.mapyourown.connect.models.ERole;
import com.mapyourown.connect.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
