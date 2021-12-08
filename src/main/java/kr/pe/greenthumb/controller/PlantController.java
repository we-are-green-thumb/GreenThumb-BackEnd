package kr.pe.greenthumb.controller;

import kr.pe.greenthumb.dto.plant.PlantDTO;
import kr.pe.greenthumb.service.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PlantController {

    private final PlantService plantService;

    // 식물 생성
    @PostMapping("/plant")
    public Long add(@RequestBody PlantDTO.Create dto) {
        return plantService.add(dto);
    }

    // 유저별 식물 조회(전체)
    @GetMapping("/user/{userId}/plant")
    public List<PlantDTO.Get> getAll(@PathVariable Long userId) {
        return plantService.getAll(userId);
    }

    // 유저별 식물 조회(하나)
    @GetMapping("/plant/{plantId}")
    public PlantDTO.Get getOne(@PathVariable Long plantId) {
        return plantService.getOne(plantId);
    }

    // 식물 수정
    @PutMapping("/plant/{plantId}")
    public Long update(@PathVariable Long plantId, @RequestBody PlantDTO.Update dto) {
        return plantService.update(plantId, dto);
    }

    // 식물 삭제
    @DeleteMapping("plant/{plantId}")
    public void delete(@RequestBody PlantDTO.Delete dto) {
        plantService.delete(dto);
    }
  
}