package kr.pe.greenthumb.controller.user;

import kr.pe.greenthumb.dto.user.BlackListDTO;
import kr.pe.greenthumb.service.user.BlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BlackListController {

    private final BlackListService blackListService;

    // 블랙리스트 생성
    @PostMapping("/user/{userId}/blacklist/blacklist{blackId}")
    public Long add(@PathVariable Long userId, @PathVariable Long blackId, @RequestBody BlackListDTO.Create dto) {
        return blackListService.add(userId, blackId, dto);
    }

}
