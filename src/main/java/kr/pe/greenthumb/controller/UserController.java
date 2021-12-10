package kr.pe.greenthumb.controller;

import kr.pe.greenthumb.dto.user.BlackListDTO;
import kr.pe.greenthumb.dto.user.UserDTO;
import kr.pe.greenthumb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // 유저 등록
    @PostMapping("/user")
    public Long add(@RequestBody UserDTO.Create dto) {
        return userService.add(dto);
    }

    // 네임 중복 체크
    @PostMapping("/user/name-check")
    public boolean check(@RequestBody UserDTO.NameCheck dto) {
        return userService.checkName(dto.getUserName());
    }

    // 닉네임 중복 체크
    @PostMapping("/user/nickname-check")
    public boolean check(@RequestBody UserDTO.NicknameCheck dto) {
        return userService.checkNickname(dto.getUserNickname());
    }

    // 모든 유저 검색
    @GetMapping("/user")
    public List<UserDTO.Get> getAll() {
        return userService.getAll();
    }

    // 유저 한명 검색
    @GetMapping("/user/{userId}")
    public UserDTO.Get getOne(@PathVariable Long userId) {
        return userService.getOne(userId);
    }

    // 유저 수정
    @PutMapping("/user/{userId}")
    public Long update(@PathVariable Long userId, @RequestBody UserDTO.Update dto) {
        return userService.update(userId, dto);
    }

    // 유저 삭제
    @DeleteMapping("/user/{userId}")
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }

    // 블랙리스트 등록
    @PostMapping("/user/{userId}/blacklist")
    public Long addBlack(@RequestBody BlackListDTO.Create dto) {
        return userService.addBlack(dto);
    }

    // 블랙리스트 전체 조회
    @GetMapping("blacklist/")
    public List<BlackListDTO.Get> getBlackList() {
        return userService.getBlackList();
    }

    // 해당 유저가 블랙스트인지 조회
    @GetMapping("/user/{userId}/blacklist/")
    public String isBlack(@RequestBody BlackListDTO.Get dto) {
        return userService.isBlack(dto);
    }

    // 블랙리스트 수정
    @PutMapping("blacklist/{blackID}")
    public Long updateBlack(@RequestBody BlackListDTO.Update dto) {
        return userService.updateBlack(dto);
    }

    // 블랙리스트 삭제
    @DeleteMapping("/blacklist/{blackID}")
    public void deleteBlack(@PathVariable Long blackID) {
        userService.deleteBlack(blackID);
    }

}