package com.ssh.greenthumb.api.controller;

import com.ssh.greenthumb.api.dto.user.BlackListDTO;
import com.ssh.greenthumb.api.dto.user.UserDTO;
import com.ssh.greenthumb.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public List<UserDTO.Get> getAll() {
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public UserDTO.Get getOne(@PathVariable Long id) {
        return userService.getOne(id);
    }

    @PutMapping("/user/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserDTO.Update dto) {
        return userService.update(id, dto);
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping("/admin/{id}/role")
    public Long updateRole(@PathVariable Long id) {
        return userService.updateRole(id);
    }

    @GetMapping("/admin/users")
    public List<UserDTO.Admin> getAllFromAdmin() {
        return userService.getAllFromAdmin();
    }

    @PostMapping("/admin/blacklist")
    public Long addBlack(@RequestBody BlackListDTO.Create dto) {
        return userService.addBlack(dto);
    }

    @GetMapping("/admin/blacklist")
    public List<BlackListDTO.Get> getBlackList() {
        return userService.getBlackList();
    }

    @PutMapping("/admin/blacklist")
    public Long updateBlack(@RequestBody BlackListDTO.Update dto) {
        return userService.updateBlack(dto);
    }

    @DeleteMapping("/admin/blacklist/{id}")
    public void deleteBlack(@PathVariable Long id) {
        userService.deleteBlack(id);
    }

}