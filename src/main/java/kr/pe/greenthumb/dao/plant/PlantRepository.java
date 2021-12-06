package kr.pe.greenthumb.dao.plant;

import kr.pe.greenthumb.domain.plant.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlantRepository extends JpaRepository<Plant, Long> {
}