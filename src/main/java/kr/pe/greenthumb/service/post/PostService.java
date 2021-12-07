package kr.pe.greenthumb.service.post;

import kr.pe.greenthumb.common.exception.NotFoundException;
import kr.pe.greenthumb.dao.post.PostRepository;
import kr.pe.greenthumb.dao.user.UserRepository;
import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.post.User;
import kr.pe.greenthumb.dto.post.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postDao;
    private final UserRepository userDao;

    @Transactional
    public Post add(PostDTO.Create dto) {
        User user = userDao.findById(dto.getUserId()).
                orElseThrow(NotFoundException::new);

        return postDao.save(dto.toEntity(user));
    }

    @Transactional
    public List<Post> getAll(String category) {
        return postDao.findPostByPostCategory(category);
    }

    @Transactional // dto로 리턴하는 법은 모르겠음...
    public PostDTO.Get getOne(Long postId) {
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new);

        PostDTO.Get dto = null;
        PostDTO.Get getDto = null;

        getDto = dto.builder()
            .title(post.getTitle())
            .postCategory(post.getPostCategory())
            .postContent(post.getPostContent())
            .postHits(post.getPostHits())
            .postCheck(post.getPostCheck())
            .build();

        return getDto;
    }

    // 1조 코드 참고
    @Transactional
    public Long update(PostDTO.Update dto, Long postId) {
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new);

        post.update(dto.getTitle(), dto.getPostCategory(), dto.getPostContent());

        return postId;
    }

    public Long updateCheck(PostDTO.UpdateCheck updateCheck) {
    }

    @Transactional
    public void delete(Long postId) {
        Post post = postDao.findById(postId)
                .orElseThrow(NotFoundException::new);

        post.delete();

        postDao.save(post);
    }

}
