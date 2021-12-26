package com.ssh.greenthumb.api.dao.plant;

import com.ssh.greenthumb.api.domain.plant.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlantHospitalRepository extends JpaRepository<Hospital, Long> {

     Optional<Hospital> findByDisease(String disease);
}