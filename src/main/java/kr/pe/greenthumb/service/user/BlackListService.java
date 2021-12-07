package kr.pe.greenthumb.service.user;

import kr.pe.greenthumb.common.exception.NotFoundException;
import kr.pe.greenthumb.dao.user.BlackListRepository;
import kr.pe.greenthumb.dao.user.UserRepository;
import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.user.BlackList;
import kr.pe.greenthumb.domain.user.User;
import kr.pe.greenthumb.dto.user.BlackListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BlackListService {

    private final UserRepository userDao;
    private final BlackListRepository blackListDao;

    // 블랙리스트 생성
    @Transactional
    public Long add(Long userId, Long blackId, BlackListDTO.Create dto) {
        User user = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        BlackList blackList = blackListDao.findById(userId).
                orElseThrow(NotFoundException::new);

        return blackListDao.save(dto.toEntity(user, blackList)).getBlackId();
    }

    // 해당 유저가 블랙리스트인지 조회
    @Transactional
    public Long get(Long userId) {
        User user = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        return blackListDao.findByUserAndBlackStatus(user, "y").getBlackId();
    }

    // 블랙리스트 수정
    @Transactional
    public Long update(Long userId, Long blackId, BlackListDTO.Update dto) {
        User user = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        BlackList blackList = blackListDao.findById(userId).
                orElseThrow(NotFoundException::new);

        blackList.update(blackId, user, dto.getBlackReason(), dto.getBlackStatus());

        return blackId;
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