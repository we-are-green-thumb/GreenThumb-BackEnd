package kr.pe.greenthumb;

import kr.pe.greenthumb.dao.plant.PlantRepository;
import kr.pe.greenthumb.common.exception.NotFoundException;
import kr.pe.greenthumb.dao.post.CommentRepository;
import kr.pe.greenthumb.dao.post.PostRepository;
import kr.pe.greenthumb.dao.user.FollowRepository;
import kr.pe.greenthumb.dao.user.UserRepository;
import kr.pe.greenthumb.domain.post.Comment;
import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.user.Follow;
import kr.pe.greenthumb.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GreenthumbApplicationTests {

    @Autowired
    private CommentRepository commentDao;
    @Autowired
    private UserRepository userDao;
    @Autowired
    private PostRepository postDao;
    @Autowired
    private PlantRepository plantDao;
    @Autowired
    private FollowRepository followDao;

//    @Test
    public void insertBaseTimeEntity() {
//        LocalDateTime now = LocalDateTime.now();

        User user = User.builder().userName("doon@doon.com").userPassword("111").userRole("회원").userNickName("ㅇㅇ").build();
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

//    @Test
//    public void test1() {
////        PlantDTO.Get dto = new PlantDTO.Get(1L,"name","nickname",1L,1L,"imageURL");
//        plantDao.findById(dto.getPlantId()).map(PlantDTO.Get::new).get();
//    }
    public void update() {

    }

//    @Test
    public void delete() {

        Comment comment = commentDao.findById(1L).orElseThrow(NotFoundException::new);

        comment.delete();

        commentDao.save(comment);

    }

    // follow test
    @Test
    public void follow() {
        User follower = User.builder().userName("follower").userPassword("aa").userRole("회원").userNickName("팔로워").build();
        userDao.save(follower);

        User followee = User.builder().userName("followee").userPassword("aa").userRole("회원").userNickName("팔로위").build();
        userDao.save(followee);

        followDao.save(Follow.builder()
                        .follower(follower)
                        .followee(followee)
                        .build());
    }

}