package kr.pe.greenthumb.service.plant;

import kr.pe.greenthumb.common.exception.NotFoundException;
import kr.pe.greenthumb.dao.plant.PlantRepository;
import kr.pe.greenthumb.dao.user.UserRepository;
import kr.pe.greenthumb.domain.plant.Plant;
import kr.pe.greenthumb.domain.user.User;
import kr.pe.greenthumb.dto.plant.PlantDTO;
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

    // 식물 생성
    @Transactional
    public Long add(PlantDTO.Create dto) {
        User user = userDao.findById(dto.getUserId()).
                orElseThrow(NotFoundException::new);

        return plantDao.save(dto.toEntity(user, dto.getPlantName(), dto.getPlantNickname(),
                dto.getWater(), dto.getTemp(), dto.getImageUrl())).getPlantId();
    }

    // 유저별 식물 조회(전체)
    @Transactional
    public List<PlantDTO.Get> getAll(PlantDTO.Get dto) {
        User user = userDao.findById(dto.getUserId()).
                orElseThrow(NotFoundException::new);

        return plantDao.findAllByUser(user).stream().map(PlantDTO.Get::new).collect(Collectors.toList());

    }

    // 유저별 식물 조회(하나)
    @Transactional
    public PlantDTO.Get getOne(PlantDTO.Get dto) {
        return plantDao.findById(dto.getPlantId()).map(PlantDTO.Get::new).get();
    }

    // 식물 수정
    @Transactional
    public Long update(PlantDTO.Update dto) {
        Plant plant = plantDao.findById(dto.getPlantId()).
                orElseThrow(NotFoundException::new);

        plant.update(dto.getPlantName(), dto.getPlantNickname(), dto.getWater(),
                dto.getTemp(), dto.getImageUrl());

        plantDao.save(plant);

        return dto.getPlantId();
    }

    // 식물 삭제
    @Transactional
    public void delete(PlantDTO.Delete dto) {
        Plant plant = plantDao.findById(dto.getPlantId()).
                orElseThrow(NotFoundException::new);

        plantDao.delete(plant);

    }

}