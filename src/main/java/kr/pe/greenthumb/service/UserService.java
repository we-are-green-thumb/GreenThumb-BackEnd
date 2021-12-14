package kr.pe.greenthumb.service;

import kr.pe.greenthumb.common.exception.NotFoundException;
import kr.pe.greenthumb.dao.user.BlackListRepository;
import kr.pe.greenthumb.dao.user.UserRepository;
import kr.pe.greenthumb.domain.user.BlackList;
import kr.pe.greenthumb.domain.user.User;
import kr.pe.greenthumb.dto.user.BlackListDTO;
import kr.pe.greenthumb.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userDao;
    private final BlackListRepository blackListDao;

    // 유저 등록
    @Transactional
    public Long add(UserDTO.Create dto) {
        return userDao.save(dto.toEntity(dto.getUserName(), dto.getUserPassword(), dto.getUserNickname())).getUserId();
    }

    // 네임 중복 체크
    public boolean checkName(String userName) {
        boolean result = false;
        User user = userDao.findByUserName(userName);

        if (user == null) {
            result = true;
        }

        // 네임 중복 : false, 사용가능 : true
        return result;
    }

    // 닉네임 중복 체크
    public boolean checkNickname(String userNickname) {
        boolean result = false;
        User user = userDao.findByUserNickname(userNickname);

        if (user == null) {
            result = true;
        }

        // 닉네임 중복 : false, 사용가능 : true
        return result;
    }


    // 모든 유저 검색
    @Transactional
    public List<UserDTO.Get> getAll() {
        return userDao.findAllByIsDeleted("n").stream().map(UserDTO.Get::new).collect(Collectors.toList());
    }

    // 유저 한명 검색
    @Transactional
    public UserDTO.Get getOne(Long userId) {
        return userDao.findById(userId).map(UserDTO.Get::new).get();
    }

    // 유저 수정
    @Transactional
    public Long update(Long userId, UserDTO.Update dto) {
        User user = userDao.findById(userId)
                .orElseThrow(NotFoundException::new);

        return user.update(dto.getUserPassword(), dto.getUserNickname()).getUserId();
    }

    @Transactional
    public Long updateRole(Long userId) {
        User user = userDao.findById(userId)
                .orElseThrow(NotFoundException::new);

        return user.updateRole().getUserId();
    }

    // 유저 삭제
    @Transactional
    public void delete(Long userId) {
        User user = userDao.findById(userId)
                .orElseThrow(NotFoundException::new);

        user.delete();
    }

    // 블랙리스트 등록
    @Transactional
    public Long addBlack(BlackListDTO.Create dto) {
        User user = userDao.findById(dto.getUserId()).
                orElseThrow(NotFoundException::new);

        //Q 이미 블랙리스트일 경우 추가 할? 말?
        if (user.getIsBlack().equals("y")) {

        }

        // User entity의 isBlack값 변경
        user.blackUser();

        return blackListDao.save(dto.toEntity(user, dto.getBlackReason())).getBlackId();
    }

    // 블랙리스트 전체 검색
    @Transactional
    public List<BlackListDTO.Get> getBlackList() {
        return blackListDao.findAll().stream().map(BlackListDTO.Get::new).collect(Collectors.toList());
    }

    // 해당 유저가 블랙리스트인지 조회
    @Transactional
    public String isBlack(Long userId) {
        User user = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        return user.getIsBlack();
    }

    // 블랙리스트 사유 수정
    @Transactional
    public Long updateBlack(BlackListDTO.Update dto) {
        BlackList blackList = blackListDao.findById(dto.getBlackId()).
                orElseThrow(NotFoundException::new);

        blackList.update(dto.getBlackReason());

        return blackList.getBlackId();
    }

    // 블랙리스트 삭제
//    @Transactional save 안해도 delete하니까 세이브 되네 !?..... userDao 세이브 안해줘도.. 되네?
    public void deleteBlack(Long blackId) {
        BlackList blackList = blackListDao.findById(blackId).
                orElseThrow(NotFoundException::new);

        blackList.getUser().nonBlackUser();

        blackListDao.delete(blackList);
    }

}