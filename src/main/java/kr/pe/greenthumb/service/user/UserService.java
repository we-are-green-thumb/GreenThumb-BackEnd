package kr.pe.greenthumb.service.user;

import kr.pe.greenthumb.dao.user.UserRepository;
import kr.pe.greenthumb.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    UserRepository userDao;

    public List<User> getAll() {
        return userDao.findAll();
    }

    public User get(Long userIdx) {
        return userDao.findById(userIdx).get();
    }

    public User add(User user) {
        return userDao.save(user);
    }

    public void update(User user) {
        userDao.save(user);
    }

    public void delete(Long userIdx) {
        User user = userDao.findById(userIdx).get();
        userDao.delete(user);
    }
}