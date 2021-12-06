package kr.pe.greenthumb.dao.plant;

import kr.pe.greenthumb.domain.plant.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}