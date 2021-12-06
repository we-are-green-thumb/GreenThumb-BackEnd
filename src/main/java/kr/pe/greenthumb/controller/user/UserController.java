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

//    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

//    @GetMapping
    public User getOne(Long userId) {
        return userService.get(userId);
    }

//    @PostMapping
    public User add(User user) {
        return userService.add(user);
    }

//    @PutMapping
    public void update(User user) {
        userService.update(user);
    }

//    @DeleteMapping
    public void delete(Long userId) {
        userService.delete(userId);
    }
}
