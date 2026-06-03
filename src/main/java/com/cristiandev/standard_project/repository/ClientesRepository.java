package com.cristiandev.standard_project.repository;

import com.cristiandev.standard_project.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository<ClienteEntity, Integer> {

}
