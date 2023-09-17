package com.example.CLIMed.repository;

import com.example.CLIMed.model.MedicoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoModel, UUID> {
}
