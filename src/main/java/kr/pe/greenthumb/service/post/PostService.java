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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postDao;
    private final UserRepository userDao;

    @Transactional
    public Long add(Long userId, PostDTO.Create dto) {
        User user = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        return postDao.save(dto.toEntity(user, dto.getTitle(), dto.getPostContent(), dto.getPostCategory())).getPostId();
    }

    @Transactional
    public List<PostDTO.Get> getAll(String postCategory) {
        return postDao.findAllPostByPostCategoryAndIsDeleted(postCategory, "n").stream().map(PostDTO.Get::new).collect(Collectors.toList());
    }

    @Transactional
    public PostDTO.Get getOne(Long postId) {
        return postDao.findById(postId).map(PostDTO.Get::new).get();
    }

    @Transactional
    public Long update(Long postId, PostDTO.Update dto) {
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new);

        post.update(dto.getTitle(), dto.getPostCategory(), dto.getPostContent());

        return postId;
    }

    @Transactional
    public Long updateCheck(Long postId, PostDTO.UpdateCheck dto) {
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new);

        post.updateCheck(dto.getIsComplete());

        return postId;
    }

    @Transactional
    public void delete(Long postId) {
        Post post = postDao.findById(postId)
                .orElseThrow(NotFoundException::new);

        post.delete();
    }

}
