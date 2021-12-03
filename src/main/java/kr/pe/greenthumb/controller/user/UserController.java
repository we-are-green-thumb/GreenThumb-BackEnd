package kr.pe.greenthumb.controller.user;

import kr.pe.greenthumb.domain.user.User;
import kr.pe.greenthumb.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    UserController() {
        System.out.println("UserController(){}");
    }

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping
    public User getUser(Long userIdx) {
        return userService.getUser(userIdx);
    }

    @PostMapping
    public User addUser(User user) {
        return userService.addUser(user);
    }

    @PutMapping
    public void updateUser(User user) {
        userService.updateUser(user);
    }

    @DeleteMapping
    public void deleteUser(Long userIdx) {
        userService.deleteUser(userIdx);
    }
}
