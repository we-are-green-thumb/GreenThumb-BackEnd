package com.ssh.greenthumb.api.service;

import com.ssh.greenthumb.api.dao.plant.PlantHospitalRepository;
import com.ssh.greenthumb.api.domain.hospital.PlantImageRequest;
import com.ssh.greenthumb.api.domain.hospital.PlantImageResponse;
import com.ssh.greenthumb.api.domain.plant.HospitalPlant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final PlantHospitalRepository hospitalDao;

    public Object getHospitalAnswer(PlantImageRequest imageUrl) {
        RestTemplate restTemplate = new RestTemplate();
        PlantImageResponse plantImageResponse = new PlantImageResponse();
        String flaskResponse;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("imageUrl", imageUrl.getImageUrl());

        ResponseEntity<String> response = restTemplate
                .postForEntity( "http://localhost:5000/predict", new HttpEntity<>(map, headers), String.class);

        flaskResponse = response.getBody();

        Optional<HospitalPlant> hospitalPlantOptional = hospitalDao.findByDisease(flaskResponse);

        if (!hospitalPlantOptional.isPresent()){
            plantImageResponse.setDisease("질병 데이터가 없습니다.");
            plantImageResponse.setDiseaseName("질병 데이터가 없습니다.");
            plantImageResponse.setContent("질병 데이터가 없습니다.");

        } else {
            plantImageResponse.setDisease(flaskResponse);
            plantImageResponse.setDiseaseName(hospitalPlantOptional.get().getDiseaseName());
            plantImageResponse.setContent(hospitalPlantOptional.get().getContent());
        }
        return plantImageResponse;
    }

}
