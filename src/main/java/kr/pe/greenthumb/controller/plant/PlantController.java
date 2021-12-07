package kr.pe.greenthumb.controller.plant;

import kr.pe.greenthumb.dto.plant.PlantDTO;
import kr.pe.greenthumb.service.plant.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PlantController {

    private final PlantService plantService;

    // 식물 생성
    @PostMapping("/plant/{plandId}")
    public Long add(@RequestBody PlantDTO.Create dto) {
        return plantService.add(dto);
    }

    // 유저별 식물 조회(전체)
    @GetMapping("/user/{userId}/plants")
    public List<PlantDTO.Get> getAll(@RequestBody PlantDTO.Get dto) {
        return plantService.getAll(dto);
    }

    // 유저별 식물 조회(하나)
    @GetMapping("/user/{userId}/plant")
    public PlantDTO.Get getOne(@RequestBody PlantDTO.Get dto) {
        return plantService.getOne(dto);
    }

    // 식물 수정
    @PutMapping("/user/{userId}/plant/{plantId}")
    public Long update(@RequestBody PlantDTO.Update dto) {
        return plantService.update(dto);
    }

    // 식물 삭제
    @DeleteMapping("/user/{userId}/plant/{plantId}")
    public void delete(@RequestBody PlantDTO.Delete dto) {
        plantService.delete(dto);
    }

}
