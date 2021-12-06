package kr.pe.greenthumb.controller.like;

import kr.pe.greenthumb.service.like.LikePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikePostController {
    @Autowired
    private LikePostService likePostService;



}
