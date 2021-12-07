package kr.pe.greenthumb.controller.plant;

import kr.pe.greenthumb.service.plant.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PlantController {

    private final PlantService plantService;

    // 식물 생성
    @Transactional("/plant/{plandId}/plant")
    public Long add(@PathVariable Long) {
        return
    }

}
