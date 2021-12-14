package com.ssh.greenthumb.service;

import com.ssh.greenthumb.domain.user.BlackList;
import com.ssh.greenthumb.dto.user.BlackListDTO;
import com.ssh.greenthumb.dto.user.UserDTO;
import com.ssh.greenthumb.common.exception.NotFoundException;
import com.ssh.greenthumb.dao.user.BlackListRepository;
import com.ssh.greenthumb.dao.user.UserRepository;
import com.ssh.greenthumb.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userDao;
    private final BlackListRepository blackListDao;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long add(UserDTO.Create dto) {
        return userDao.save(dto.toEntity(dto.getEmail(), passwordEncoder.encode(dto.getPassword()), dto.getNickName(), dto.getImageUrl(), dto.getProviderId())).getId();
    }

    //Q 단순 get은 transactional 뺄까?
    @Transactional
    public List<UserDTO.Get> getAll() {
        return userDao.findAllByIsDeleted("n").stream().map(UserDTO.Get::new).collect(Collectors.toList());
    }

    @Transactional
    public UserDTO.Get getOne(Long userId) {
        return userDao.findById(userId).map(UserDTO.Get::new).get();
    }

    @Transactional
    public Long update(Long userId, UserDTO.Update dto) {
        User user = userDao.findById(userId)
                .orElseThrow(NotFoundException::new);

        return user.update(dto.getUserPassword(), dto.getUserNickname(), dto.getImageUrl()).getId();
    }

    @Transactional
    public Long updateRole(Long userId) {
        User user = userDao.findById(userId)
                .orElseThrow(NotFoundException::new);

        return user.updateRole().getId();
    }

    @Transactional
    public void delete(Long userId) {
        User user = userDao.findById(userId)
                .orElseThrow(NotFoundException::new);

        user.delete();
    }

    public List<UserDTO.GetFromAdmin> getAllFromAdmin(){
        return userDao.findAll().stream().map(UserDTO.GetFromAdmin::new).collect(Collectors.toList());
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

        return blackListDao.save(dto.toEntity(user, dto.getReason())).getId();
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
        BlackList blackList = blackListDao.findById(dto.getId()).
                orElseThrow(NotFoundException::new);

        blackList.update(dto.getReason());

        return blackList.getId();
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