package kr.pe.greenthumb.controller.plant;

import kr.pe.greenthumb.service.plant.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PlantController {

    private final PlantService plantService;

}
