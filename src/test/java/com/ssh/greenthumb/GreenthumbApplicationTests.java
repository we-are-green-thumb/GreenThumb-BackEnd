package com.ssh.greenthumb;

import com.ssh.greenthumb.api.dao.plant.PlantRepository;
import com.ssh.greenthumb.api.dao.post.CommentRepository;
import com.ssh.greenthumb.api.dao.post.PostRepository;
import com.ssh.greenthumb.api.dao.user.FollowRepository;
import com.ssh.greenthumb.api.dao.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Autowired
    PasswordEncoder passwordEncoder;

//    @Test
//    public void insertBaseTimeEntity() {
//        LocalDateTime now = LocalDateTime.now();

//        User user = User.builder().userName("doon@doon.com").userPassword("111").userRole("회원").userNickName("ㅇㅇ").build();
//        userDao.save(user);
//        Post post = Post.builder().postCategory("질문").postContent("첫번째 게시글").user(user).title("일빠다").build();
//        postDao.save(post);
//
//        commentDao.save(Comment.builder()
//                .post(post)
//                .user(user)
//                .commentContent("안녕")
//                .build());
//
//        List<Comment> commentList = commentDao.findAll();
//
//        Comment comment = commentList.get(0);
//
//        System.out.println(">>>>>>>>> createDate=" + user.getCreatedDate() + ", modifiedDate = " + user.getModifiedDate() + "<<<<<<<<<<");

//    }
//    void contextLoads() {
//    }

//    @Test
//    public void test1() {
////        PlantDTO.Get dto = new PlantDTO.Get(1L,"name","nickname",1L,1L,"imageURL");
//        plantDao.findById(dto.getPlantId()).map(PlantDTO.Get::new).get();
//    }
//    public void update() {
//
//    }

//    @Test
//    public void delete() {

//        Comment comment = commentDao.findById(1L).orElseThrow(NotFoundException::new);
//
//        comment.delete();
//
//        commentDao.save(comment);

//    }


//    @Test
//    public void follow() {
//
////        User follower = User.builder().nickName("follower").password(passwordEncoder.encode("abc")).email("ab@aa.com").role(Role.USER).provider(AuthProvider.local).build();
////        userDao.save(follower);
////
//        User admin = User.builder().nickName("admin").password(passwordEncoder.encode("admin")).email("admin@a.com").role(Role.ADMIN).provider(AuthProvider.local).build();
//        userDao.save(admin);
//
////        OAuthUser oAuthUser = OAuthUser.builder().name("aaa").email("aaa@aaa.com").password("1234").imageUrl("aa").provider(AuthProvider.LOCAL).role(Role.USER).emailVerified(true).providerId("1111").build();
////        oAuthUserDao.save(oAuthUser);
//
////         User follower = userDao.findById(2L).get();
////         User followee = userDao.findById(1L).get();
////
////         followDao.save(Follow.builder()
////                         .follower(follower)
////                         .followee(followee)
////                         .build());
//
//     }

    }