package kr.pe.greenthumb.dao.plant;

import kr.pe.greenthumb.domain.plant.Plant;
import kr.pe.greenthumb.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Long> {

    Plant findByUser(User user);

    List<Plant> findAllByUser(User user);

}