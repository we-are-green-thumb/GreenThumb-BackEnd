package kr.pe.greenthumb.controller;

import kr.pe.greenthumb.dto.user.BlackListDTO;
import kr.pe.greenthumb.dto.user.UserDTO;
import kr.pe.greenthumb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin(origins = {"*"})
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    public Long add(@RequestBody UserDTO.Create dto) {
        return userService.add(dto);
    }

    //Q 유저정보 모두 출력할 때, userId도 필요할까?
    @GetMapping
    public List<UserDTO.Get> getAll() {
        return userService.getAll();
    }

    //Q 유저정보 출력할 때, userId도 필요할까?
    @GetMapping("/{userId}")
    public UserDTO.Get getOne(@PathVariable Long userId) {
        return userService.getOne(userId);
    }

    //Q 업데이트한 항목 확인하는 게 좋을 것 같은데.. dto map에러 발생해서 그냥 둠
    @PutMapping("/{userId}")
    public Long update(@PathVariable Long userId, @RequestBody UserDTO.Update dto) {
        return userService.update(userId, dto);
    }

    @PutMapping("/{userId}/role")
    public Long updateRole(@PathVariable Long userId) {
        return userService.updateRole(userId);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }

    // 블랙리스트 등록
    @PostMapping("/blacklist")
    public Long addBlack(@RequestBody BlackListDTO.Create dto) {
        return userService.addBlack(dto);
    }

    // 블랙리스트 전체 조회
    //Q userId만 받아올까 아님 닉네임, 사유까지 다 ?
    @GetMapping("/blacklist")
    public List<BlackListDTO.Get> getBlackList() {
        return userService.getBlackList();
    }

    // 해당 유저가 블랙리스트인지 조회
    @GetMapping("/{userId}/blacklist")
    public String isBlack(@PathVariable Long userId) {
        return userService.isBlack(userId);
    }

    // 블랙리스트 사유 수정
    @PutMapping("/blacklist/black_update")
    public Long updateBlack(@RequestBody BlackListDTO.Update dto) {
        return userService.updateBlack(dto);
    }

    // 블랙리스트 삭제
    @DeleteMapping("/blacklist/{blackID}")
    public void deleteBlack(@PathVariable Long blackID) {
        userService.deleteBlack(blackID);
    }

}