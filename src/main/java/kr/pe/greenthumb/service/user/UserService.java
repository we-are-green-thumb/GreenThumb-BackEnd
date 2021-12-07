package kr.pe.greenthumb.service.user;

import kr.pe.greenthumb.common.exception.NotFoundException;
import kr.pe.greenthumb.dao.user.UserRepository;
import kr.pe.greenthumb.domain.user.User;
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

    @Transactional
    public Long add(UserDTO.Create dto) {
        return userDao.save(dto.toEntity(dto.getUserName(), dto.getUserPassword(), dto.getUserNickname())).getUserId();
    }

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

        user.Update(dto.getUserPassword(), dto.getUserNickname());

        return userId;
    }

    @Transactional
    public void delete(Long userId) {
        User user = userDao.findById(userId)
                .orElseThrow(NotFoundException::new);

        user.delete();
    }

}