package com.ssh.greenthumb.api.controller;

import com.ssh.greenthumb.api.dao.user.UserRepository;
import com.ssh.greenthumb.api.domain.user.User;
import com.ssh.greenthumb.api.dto.user.BlackListDTO;
import com.ssh.greenthumb.api.dto.user.UserDTO;
import com.ssh.greenthumb.api.security.CurrentUser;
import com.ssh.greenthumb.api.security.TokenProvider;
import com.ssh.greenthumb.api.security.UserPrincipal;
import com.ssh.greenthumb.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.ResourceNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userDao;

    //Q 유저정보 모두 출력할 때, userId도 필요할까?
    @GetMapping
    public List<UserDTO.Get> getAll() {
        return userService.getAll();
    }

    //Q 유저정보 출력할 때, userId도 필요할까?
    @GetMapping("/user/{userId}")
    public UserDTO.Get getOne(@PathVariable Long userId) {
        return userService.getOne(userId);
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userDao.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    //Q 업데이트한 항목 확인하는 게 좋을 것 같은데.. dto map에러 발생해서 그냥 둠
    @PutMapping("/user/{userId}")
    public Long update(@PathVariable Long userId, @RequestBody UserDTO.Update dto) {
        return userService.update(userId, dto);
    }

    @PutMapping("/admin/{userId}/role")
    public Long updateRole(@PathVariable Long userId) {
        return userService.updateRole(userId);
    }

    @DeleteMapping("/user/{userId}")
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }

    @GetMapping("/admin/users")
    public List<UserDTO.GetFromAdmin> getAllFromAdmin() {
        return userService.getAllFromAdmin();
    }

    // 이메일 중복 체크
    @GetMapping("/user/email")
    public boolean checkEmail(@RequestBody UserDTO.CheckEmail dto) {
        return userService.checkEmail(dto.getEmail());
    }

    // 닉네임 중복 체크
    @GetMapping("/user/nickName")
    public boolean checkNickName(@RequestBody UserDTO.CheckNickName dto) {
        return userService.checkNickName(dto.getNickName());
    }

    // 블랙리스트 등록
    @PostMapping("/admin/blacklist")
    public Long addBlack(@RequestBody BlackListDTO.Create dto) {
        return userService.addBlack(dto);
    }

    // 블랙리스트 전체 조회
    //Q userId만 받아올까 아님 닉네임, 사유까지 다 ?
    @GetMapping("/admin/blacklist")
    public List<BlackListDTO.Get> getBlackList() {
        return userService.getBlackList();
    }

    // 해당 유저가 블랙리스트인지 조회
    @GetMapping("/admin/{userId}/blacklist")
    public String isBlack(@PathVariable Long userId) {
        return userService.isBlack(userId);
    }

    // 블랙리스트 사유 수정
    @PutMapping("/admin/blacklist")
    public Long updateBlack(@RequestBody BlackListDTO.Update dto) {
        return userService.updateBlack(dto);
    }

    // 블랙리스트 삭제
    @DeleteMapping("/admin/blacklist/{blackID}")
    public void deleteBlack(@PathVariable Long blackID) {
        userService.deleteBlack(blackID);
    }

}