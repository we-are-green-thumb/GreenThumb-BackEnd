package com.ssh.greenthumb.dao.plant;

import com.ssh.greenthumb.domain.plant.Plant;
import com.ssh.greenthumb.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlantRepository extends JpaRepository<Plant, Long> {

    Plant findByUser(User user);

    List<Plant> findAllByUser(User user);

}