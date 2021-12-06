package kr.pe.greenthumb;

import kr.pe.greenthumb.dao.post.CommentRepository;
import kr.pe.greenthumb.dao.post.PostRepository;
import kr.pe.greenthumb.dao.user.UserRepository;
import kr.pe.greenthumb.domain.post.Comment;
import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class GreenthumbApplicationTests {

    @Autowired
    private CommentRepository commentDao;
    @Autowired
    private UserRepository userDao;
    @Autowired
    private PostRepository postDao;

    @Test
    public void insertBaseTimeEntity() {
        LocalDateTime now = LocalDateTime.now();

        User user = User.builder().userName("doon@doon.com").userPassword("111").userRole("회원").build();
        userDao.save(user);
        Post post = Post.builder().postCategory("질문").postContent("첫번째 게시글").user(user).title("일빠다").build();
        postDao.save(post);

        commentDao.save(Comment.builder()
                .post(post)
                .user(user)
                .commentContent("안녕")
                .build());

        List<Comment> commentList = commentDao.findAll();

        Comment comment = commentList.get(0);

        System.out.println(">>>>>>>>> createDate=" + user.getCreatedDate() + ", modifiedDate = " + user.getModifiedDate() + "<<<<<<<<<<");

    }
//    void contextLoads() {
//    }

}
