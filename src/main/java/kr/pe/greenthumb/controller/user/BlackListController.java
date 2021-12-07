package kr.pe.greenthumb.controller.user;

import kr.pe.greenthumb.dto.user.BlackListDTO;
import kr.pe.greenthumb.service.user.BlackListService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BlackListController {

    private final BlackListService blackListService;

    // 블랙리스트 생성
    @PostMapping("/user/{userId}/blacklist")
    public Long add(@PathVariable Long userId, @PathVariable Long blackId, @RequestBody BlackListDTO.Create dto) {
        return blackListService.add(userId, blackId, dto);
    }

    // 해당 유저가 블랙스트인지 조회
    @GetMapping("/user/{userId}/blacklist/blacklist{blackID}")
    public Long get(@PathVariable Long userId) {
        return blackListService.get(userId);
    }

    // 블랙리스트 수정
    @PutMapping("/user/{userId}/blacklist/blacklist{blackID}/blacklist")
    public Long Update(@PathVariable Long userID, @PathVariable Long blackId, @RequestBody BlackListDTO.Update dto) {
        return blackListService.update(userID, blackId, dto);
    }

     // 블랙리스트 삭제
    @DeleteMapping("/blacklist/blacklist{blackID}")
    public void delete(@PathVariable Long blackID) {
        blackListService.delete(blackID);
    }

}