package com.cristiandev.standard_project.repository;

import com.cristiandev.standard_project.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<ClientEntity, String> {

}
