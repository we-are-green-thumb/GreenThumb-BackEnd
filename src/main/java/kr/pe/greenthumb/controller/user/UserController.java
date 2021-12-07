package kr.pe.greenthumb.controller.user;

import kr.pe.greenthumb.dto.user.UserDTO;
import kr.pe.greenthumb.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public Long add(@RequestBody UserDTO.Create dto) {
        return userService.add(dto);
    }

    @GetMapping("/user")
    public List<UserDTO.Get> getAll() {
        return userService.getAll();
    }

    @GetMapping("/user/{userId}")
    public UserDTO.Get getOne(@PathVariable Long userId) {
        return userService.getOne(userId);
    }

    @PutMapping("/user/{userId}")
    public Long update(@PathVariable Long userId, @RequestBody UserDTO.Update dto) {
        return userService.update(userId, dto);
    }

    @DeleteMapping("/user/{userId}")
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }

}
