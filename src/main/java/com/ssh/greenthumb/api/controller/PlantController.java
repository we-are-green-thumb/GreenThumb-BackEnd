package com.ssh.greenthumb.api.controller;

import com.ssh.greenthumb.api.dto.plant.PlantDTO;
import com.ssh.greenthumb.api.service.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/plant")
@RestController
//@CrossOrigin(origins = {"*"})
public class PlantController {

    private final PlantService plantService;

    // 식물 생성
    @PostMapping
    public Long add(@RequestBody PlantDTO.Create dto) {
        return plantService.add(dto);
    }

    @GetMapping()
    public List<PlantDTO.Get> getAll() {
        return plantService.getAll();
    }

    // 유저별 식물 조회(전체)
    @GetMapping("/user/{userId}")
    public List<PlantDTO.Get> getAllByUser(@PathVariable Long userId, HttpServletRequest request) {
        return plantService.getAllByUser(userId, request);
    }

    @GetMapping("/plant-name/{name}")
    public List<PlantDTO.Get> getAllByName(@PathVariable String name) {
        return plantService.getAllByName(name);
    }

    // 유저별 식물 조회(하나) - 식물 상세
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