package kr.pe.greenthumb.service.user;

import kr.pe.greenthumb.common.exception.NotFoundException;
import kr.pe.greenthumb.dao.user.BlackListRepository;
import kr.pe.greenthumb.dao.user.UserRepository;
import kr.pe.greenthumb.domain.user.BlackList;
import kr.pe.greenthumb.domain.user.User;
import kr.pe.greenthumb.dto.user.BlackListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BlackListService {

    private final UserRepository userDao;
    private final BlackListRepository blackListDao;

    // 블랙리스트 등록
    @Transactional
    public Long add(BlackListDTO.Create dto) {
        User user = userDao.findById(dto.getUserId()).
                orElseThrow(NotFoundException::new);

        return blackListDao.save(dto.toEntity(user, dto.getBlackReason())).getBlackId();
    }

    // 블랙리스트 전체 검색
    @Transactional
    public List<BlackListDTO.Get> getAll() {
        return blackListDao.findAll().stream().map(BlackListDTO.Get::new).collect(Collectors.toList());
    }

    // 해당 유저가 블랙리스트인지 조회
    @Transactional
    public String getOne(BlackListDTO.Get dto) {
        User user = userDao.findById(dto.getUserId()).
                orElseThrow(NotFoundException::new);

        return blackListDao.findByUserAndBlackStatus(user, "y").getBlackStatus();
    }

    // 블랙리스트 사유 수정
    @Transactional
    public Long update(BlackListDTO.Update dto) {
        BlackList blackList = blackListDao.findById(dto.getBlackId()).
                orElseThrow(NotFoundException::new);

        blackList.update(dto.getBlackReason());

        blackListDao.save(blackList);

        return blackList.getBlackId();
    }

    // 블랙리스트 삭제
    @Transactional
    public void delete(Long blackId) {
        BlackList blackList = blackListDao.findById(blackId).
                orElseThrow(NotFoundException::new);

        blackList.delete();

        blackListDao.save(blackList);
    }

}