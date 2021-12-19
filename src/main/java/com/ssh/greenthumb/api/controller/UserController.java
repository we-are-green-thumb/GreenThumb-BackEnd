package com.ssh.greenthumb.api.controller;

import com.ssh.greenthumb.api.dto.user.BlackListDTO;
import com.ssh.greenthumb.api.dto.user.UserDTO;
import com.ssh.greenthumb.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "사용자 API")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @Operation(summary = "사용자 전체 조회", description = "전체 사용자의 정보를 조회합니다.")
    @GetMapping("/user")
    public List<UserDTO.Get> getAll() {
        return userService.getAll();
    }

    @Operation(summary = "사용자 상세 조회", description = "사용자 id로 상세 정보를 조회합니다.")
    @GetMapping("/user/{id}")
    public UserDTO.Get getOne(@PathVariable Long id) {
        return userService.getOne(id);
    }

    @Operation(summary = "내 정보 수정", description = "사용자 id로 닉네임, 자기어필 정보를 수정합니다.")
    @PutMapping("/user/{id}")
    public Long update(@PathVariable Long id, @RequestBody UserDTO.Update dto) {
        return userService.update(id, dto);
    }

    @Operation(summary = "사용자 삭제", description = "사용자 id로 정보를 삭제합니다.")
    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @Operation(summary = "사용자 권한 수정", description = "관리자가 사용자의 id로 사용자 권한을 수정합니다.")
    @PutMapping("/admin/user/{id}/role")
    public Long updateRole(@PathVariable Long id) {
        return userService.updateRole(id);
    }

    @Operation(summary = "사용자 전체 조회(관리자 권한)", description = "관리자가 모든 사용자의 정보를 조회합니다.")
    @GetMapping("/admin/users")
    public List<UserDTO.Admin> getAllFromAdmin() {
        return userService.getAllFromAdmin();
    }

    @Operation(summary = "블랙리스트 등록", description = "관리자가 사용자의 id와 블랙리스트에 등록되는 이유를 입력하여 블랙리스트에 등록합니다.")
    @PostMapping("/admin/blacklist")
    public Long addBlack(@RequestBody BlackListDTO.Create dto) {
        return userService.addBlack(dto);
    }

    @Operation(summary = "블랙리스트 전체 조회", description = "관리자가 블랙리스트에 등록된 사용자를 전체 조회 합니다.")
    @GetMapping("/admin/blacklist")
    public List<BlackListDTO.Get> getBlackList() {
        return userService.getBlackList();
    }

    @Operation(summary = "블랙리스트 수정", description = "관리자가 블랙리스트에 등록된 사용자의 id로 이유를 수정합니다.")
    @PutMapping("/admin/blacklist")
    public Long updateBlack(@RequestBody BlackListDTO.Update dto) {
        return userService.updateBlack(dto);
    }

    @Operation(summary = "블랙리스트 삭제", description = "관리자가 사용자의 id로 블랙리스트에서 삭제합니다.")
    @DeleteMapping("/admin/blacklist/{id}")
    public void deleteBlack(@PathVariable Long id) {
        userService.deleteBlack(id);
    }

}