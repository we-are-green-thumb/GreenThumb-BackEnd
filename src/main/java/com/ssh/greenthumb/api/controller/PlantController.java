package com.ssh.greenthumb.api.controller;

import com.ssh.greenthumb.api.dto.plant.PlantDTO;
import com.ssh.greenthumb.api.service.PlantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PlantController {

    private final PlantService plantService;

    @PostMapping("/user/{id}/plant")
    public Long add(@PathVariable Long id, @RequestBody PlantDTO.Create dto) {
        return plantService.add(id, dto);
    }

    @GetMapping("/plants")
    public List<PlantDTO.Get> getAll() {
        return plantService.getAll();
    }

    @GetMapping("/user/{id}/plants")
    public List<PlantDTO.Get> getAllByUser(@PathVariable Long id) {
        return plantService.getAllByUser(id);
    }

    @GetMapping("/plant-name/{name}")
    public List<PlantDTO.Get> getAllByName(@PathVariable String name) {
        return plantService.getAllByName(name);
    }

    // 유저별 식물 조회(하나) - 식물 상세
    @GetMapping("/plant/{plantId}")
    public PlantDTO.Get getOneByUser(@PathVariable Long plantId) {
        return plantService.getOneByUser(plantId);
    }

    @PutMapping("/user/{userId}/plant/{plantId}")
    public Long update(@PathVariable Long userId, @PathVariable Long plantId, @RequestBody PlantDTO.Update dto) {
        return plantService.update(plantId, dto);
    }

    @DeleteMapping("/user/{userId}/plant/{plantId}")
    public void delete(@PathVariable Long userId, @PathVariable Long plantId) {
        plantService.delete(plantId);
    }
  
}