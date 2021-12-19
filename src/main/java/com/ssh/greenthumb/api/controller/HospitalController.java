package com.ssh.greenthumb.api.controller;

import com.ssh.greenthumb.api.domain.hospital.PlantImageRequest;
import com.ssh.greenthumb.api.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping("/plant-hospital")
    public Object DiseaseAnswer(@RequestBody PlantImageRequest imageUrl) {
        return hospitalService.getHospitalAnswer(imageUrl);
    }
}
