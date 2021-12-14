package kr.pe.greenthumb.controller;

import kr.pe.greenthumb.dto.user.BlackListDTO;
import kr.pe.greenthumb.dto.user.UserDTO;
import kr.pe.greenthumb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

<<<<<<< HEAD
    // 유저 등록
    @PostMapping("/user")
=======
    @PostMapping
>>>>>>> c18116ba57b6abd0a279f4167146da1d48e1b720
    public Long add(@RequestBody UserDTO.Create dto) {
        return userService.add(dto);
    }

<<<<<<< HEAD
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
=======
    //Q 유저정보 모두 출력할 때, userId도 필요할까?
    @GetMapping
>>>>>>> c18116ba57b6abd0a279f4167146da1d48e1b720
    public List<UserDTO.Get> getAll() {
        return userService.getAll();
    }

<<<<<<< HEAD
    // 유저 한명 검색
    @GetMapping("/user/{userId}")
=======
    //Q 유저정보 출력할 때, userId도 필요할까?
    @GetMapping("/{userId}")
>>>>>>> c18116ba57b6abd0a279f4167146da1d48e1b720
    public UserDTO.Get getOne(@PathVariable Long userId) {
        return userService.getOne(userId);
    }

<<<<<<< HEAD
    // 유저 수정
    @PutMapping("/user/{userId}")
=======
    //Q 업데이트한 항목 확인하는 게 좋을 것 같은데.. dto map에러 발생해서 그냥 둠
    @PutMapping("/{userId}")
>>>>>>> c18116ba57b6abd0a279f4167146da1d48e1b720
    public Long update(@PathVariable Long userId, @RequestBody UserDTO.Update dto) {
        return userService.update(userId, dto);
    }

<<<<<<< HEAD
    // 유저 삭제
    @DeleteMapping("/user/{userId}")
=======
    @PutMapping("/{userId}/role")
    public Long updateRole(@PathVariable Long userId) {
        return userService.updateRole(userId);
    }

    @DeleteMapping("/{userId}")
>>>>>>> c18116ba57b6abd0a279f4167146da1d48e1b720
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

<<<<<<< HEAD
    // 블랙리스트 수정
    @PutMapping("blacklist/{blackID}")
=======
    // 블랙리스트 사유 수정
    @PutMapping("/blacklist/black_update")
>>>>>>> c18116ba57b6abd0a279f4167146da1d48e1b720
    public Long updateBlack(@RequestBody BlackListDTO.Update dto) {
        return userService.updateBlack(dto);
    }

    // 블랙리스트 삭제
    @DeleteMapping("/blacklist/{blackID}")
    public void deleteBlack(@PathVariable Long blackID) {
        userService.deleteBlack(blackID);
    }

}