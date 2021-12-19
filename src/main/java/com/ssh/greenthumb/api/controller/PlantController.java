package com.ssh.greenthumb.api.controller;

import com.ssh.greenthumb.api.dto.plant.PlantDTO;
import com.ssh.greenthumb.api.service.PlantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Plant", description = "식물 API")
@RequiredArgsConstructor
@RestController
public class PlantController {

    private final PlantService plantService;

    @Operation(summary = "식물 등록", description = "사용자 id와 식물 이름, 닉네임, 물 주기, 온도, 식물 이미지 경로를 입력하여 식물을 등록합니다.")
    @PostMapping("/user/{id}/plant")
    public Long add(@PathVariable Long id, @RequestBody PlantDTO.Create dto) {
        return plantService.add(id, dto);
    }

    @Operation(summary = "식물 전체 조회", description = "등록된 식물을 전체 조회 합니다.")
    @GetMapping("/plants")
    public List<PlantDTO.Get> getAll() {
        return plantService.getAll();
    }

    @Operation(summary = "사용자별 식물 전체 조회", description = "사용자 id로 해당 사용자가 등록한 식물들을 전체 조회 합니다.")
    @GetMapping("/user/{id}/plants")
    public List<PlantDTO.Get> getAllByUser(@PathVariable Long id) {
        return plantService.getAllByUser(id);
    }

    @Operation(summary = "식물 이름별 전체 조회", description = "식물의 이름을 입력하여 해당 식물을 전체 조회 합니다.")
    @GetMapping("/plant-name/{name}")
    public List<PlantDTO.Get> getAllByName(@PathVariable String name) {
        return plantService.getAllByName(name);
    }

    @Operation(summary = "식물 상세 조회", description = "식물 id로 상세 정보를 조회합니다.")
    @GetMapping("/plant/{plantId}")
    public PlantDTO.Get getOneByUser(@PathVariable Long plantId) {
        return plantService.getOneByUser(plantId);
    }

    @Operation(summary = "식물 수정", description = "사용자 id와 식물 id로 식물 정보를 수정합니다.")
    @PutMapping("/user/{userId}/plant/{plantId}")
    public Long update(@PathVariable Long userId, @PathVariable Long plantId, @RequestBody PlantDTO.Update dto) {
        return plantService.update(plantId, dto);
    }

    @Operation(summary = "식물 삭제", description = "사용자의 id와 식물 id로 식물 정보를 삭제합니다.")
    @DeleteMapping("/user/{userId}/plant/{plantId}")
    public void delete(@PathVariable Long userId, @PathVariable Long plantId) {
        plantService.delete(plantId);
    }
  
}