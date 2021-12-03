package kr.pe.greenthumb.service.user;

import kr.pe.greenthumb.dao.user.UserRepository;
import kr.pe.greenthumb.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userDao;

    public List<User> getAllUser() {
        return userDao.findAll();
    }

    public User getUser(Long userIdx) {
        return userDao.findById(userIdx).get();
    }

    public User addUser(User user) {
        return userDao.save(user);
    }

    public void updateUser(User user) {
        userDao.save(user);
    }

    public void deleteUser(Long userIdx) {
        User user = userDao.findById(userIdx).get();
        userDao.delete(user);
    }
}