package kr.pe.greenthumb.controller;

import kr.pe.greenthumb.dto.plant.PlantDTO;
import kr.pe.greenthumb.service.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/plant")
@RestController
public class PlantController {

    private final PlantService plantService;

    // 식물 생성
    @PostMapping
    public Long add(@RequestBody PlantDTO.Create dto) {
        return plantService.add(dto);
    }

    @GetMapping("/all")
    public List<PlantDTO.Get> getAll() {
        return plantService.getAll();
    }

    // 유저별 식물 조회(전체)
    @GetMapping("/user/{userId}/plants")
    public List<PlantDTO.Get> getAllByUser(@PathVariable Long userId) {
        return plantService.getAllByUser(userId);
    }

    // 유저별 식물 조회(하나)
    @GetMapping("/{plantId}")
    public PlantDTO.Get getOneByUser(@PathVariable Long plantId) {
        return plantService.getOneByUser(plantId);
    }

    // 식물 수정
    @PutMapping("/{plantId}")
    public Long update(@PathVariable Long plantId, @RequestBody PlantDTO.Update dto) {
        return plantService.update(plantId, dto);
    }

    // 식물 삭제
    @DeleteMapping("/{plantId}")
    public void delete(@PathVariable Long plantId) {
        plantService.delete(plantId);
    }
  
}