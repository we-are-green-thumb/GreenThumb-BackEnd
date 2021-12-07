package kr.pe.greenthumb.controller.user;

import kr.pe.greenthumb.dto.user.BlackListDTO;
import kr.pe.greenthumb.service.user.BlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlackListController {

    private final BlackListService blackListService;

    // 블랙리스트 등록
    @PostMapping("/user/{userId}/blacklist")
    public Long add(@RequestBody BlackListDTO.Create dto) {
        return blackListService.add(dto);
    }

    // 블랙리스트 전체 조회
    @GetMapping("blacklist/")
    public List<BlackListDTO.Get> getAll() {
        return blackListService.getAll();
    }

    // 해당 유저가 블랙스트인지 조회
    @GetMapping("/user/{userId}/blacklist/blacklist{blackID}")
    public String getOne(@RequestBody BlackListDTO.Get dto) {
        return blackListService.getOne(dto);
    }

    // 블랙리스트 수정
    @PutMapping("blacklist/blacklist{blackID}")
    public Long update( @RequestBody BlackListDTO.Update dto) {
        return blackListService.update(dto);
    }

     // 블랙리스트 삭제
    @DeleteMapping("/blacklist/blacklist{blackID}")
    public void delete(@PathVariable Long blackID) {
        blackListService.delete(blackID);
    }

}