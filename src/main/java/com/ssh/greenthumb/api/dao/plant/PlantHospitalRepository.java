package com.ssh.greenthumb.api.dao.plant;

import com.ssh.greenthumb.api.domain.plant.HospitalPlant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlantHospitalRepository extends JpaRepository<HospitalPlant, Long> {

     Optional<HospitalPlant> findByDisease(String disease);
}