package com.cristiandev.standard_project.repository;

import com.cristiandev.standard_project.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<PlayerEntity, String> {

}
