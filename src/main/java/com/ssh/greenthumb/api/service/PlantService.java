package com.ssh.greenthumb.api.service;

import com.ssh.greenthumb.api.common.exception.NotFoundException;
import com.ssh.greenthumb.api.dao.plant.PlantRepository;
import com.ssh.greenthumb.api.dao.user.UserRepository;
import com.ssh.greenthumb.api.domain.plant.Plant;
import com.ssh.greenthumb.api.domain.user.User;
import com.ssh.greenthumb.api.dto.plant.PlantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlantService {

    private final UserRepository userDao;
    private final PlantRepository plantDao;

    @Transactional
    public Long add(Long id, PlantDTO.Create dto) {
        User user = userDao.findById(id).
                orElseThrow(NotFoundException::new);

        return plantDao.save(dto.toEntity(user, dto.getName(), dto.getNickName(),
                dto.getWater(), dto.getTemp(), dto.getImageUrl())).getId();
    }

    @Transactional
    public List<PlantDTO.Get> getAll() {
        return plantDao.findAll().stream().map(PlantDTO.Get::new).collect(Collectors.toList());
    }

    @Transactional
    public List<PlantDTO.Get> getAllByUser(Long id) {

            User user = userDao.findById(id).
                    orElseThrow(NotFoundException::new);

            return plantDao.findAllByUser(user).stream().map(PlantDTO.Get::new).collect(Collectors.toList());
    }

    public List<PlantDTO.Get> getAllByName(String name) {
        return plantDao.findAllByName(name).stream().map(PlantDTO.Get::new).collect(Collectors.toList());
    }

    @Transactional
    public PlantDTO.Get getOneByUser(Long plantId) {
        return plantDao.findById(plantId).map(PlantDTO.Get::new).get();
    }

    @Transactional
    public Long update(Long plantId, PlantDTO.Update dto) {
        Plant plant = plantDao.findById(plantId).
                orElseThrow(NotFoundException::new);

        plant.update(dto.getName(), dto.getNickName(), dto.getWater(),
                dto.getTemp(), dto.getImageUrl());

        return plantId;
    }

    @Transactional
    public void delete(Long plantId) {
        Plant plant = plantDao.findById(plantId).
                orElseThrow(NotFoundException::new);

        plantDao.delete(plant);
    }

}