package com.solid12.solid12.adapters.dbrelational;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICitizenRepository extends JpaRepository<CitizenEntity, Long> {

    Optional<CitizenEntity> findByDocumentNumber(String documentNumber);
}
