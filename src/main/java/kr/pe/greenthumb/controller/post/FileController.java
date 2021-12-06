package kr.pe.greenthumb.controller.post;

import kr.pe.greenthumb.service.post.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FileController {

    private final CommentService commentService;
}
