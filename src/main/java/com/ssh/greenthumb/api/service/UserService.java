package com.ssh.greenthumb.api.service;

import com.ssh.greenthumb.api.common.exception.NotFoundException;
import com.ssh.greenthumb.api.dao.post.CommentRepository;
import com.ssh.greenthumb.api.dao.post.PostRepository;
import com.ssh.greenthumb.api.dao.user.BlackListRepository;
import com.ssh.greenthumb.api.dao.user.UserRepository;
import com.ssh.greenthumb.api.domain.post.Comment;
import com.ssh.greenthumb.api.domain.post.Post;
import com.ssh.greenthumb.api.domain.user.BlackList;
import com.ssh.greenthumb.api.domain.user.User;
import com.ssh.greenthumb.api.dto.user.BlackListDTO;
import com.ssh.greenthumb.api.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userDao;
    private final BlackListRepository blackListDao;
    private final PostRepository postDao;
    private final CommentRepository commentDao;

    @Transactional
    public List<UserDTO.Get> getAll() {
        return userDao.findAllByIsDeleted("n").stream().map(UserDTO.Get::new).collect(Collectors.toList());
    }

    @Transactional
    public UserDTO.Get getOne(Long id) {
        return userDao.findById(id).map(UserDTO.Get::new).get();
    }

    @Transactional
    public Long update(Long id, UserDTO.Update dto) {
        User user = userDao.findById(id)
                .orElseThrow(NotFoundException::new);

        return user.update(dto.getNickName(), dto.getProfile()).getId();
    }

    @Transactional
    public Long updateRole(Long id) {
        User user = userDao.findById(id)
                .orElseThrow(NotFoundException::new);

        return user.updateRole().getId();
    }

    @Transactional
    public void delete(Long id) {
        User user = userDao.findById(id)
                .orElseThrow(NotFoundException::new);

        List<Post> postList = postDao.findByUser(user);

        user.delete();
        user.updateRole();

        for(Post p : postList) {
            p.delete();

            List<Comment> commentList = commentDao.findAllByPostAndUserAndIsDeleted(p, user,"y");

            for(Comment c : commentList) c.delete();
        }
    }

    public List<UserDTO.Admin> getAllFromAdmin(){
        return userDao.findAll().stream().map(UserDTO.Admin::new).collect(Collectors.toList());
    }

    @Transactional
    public Long addBlack(BlackListDTO.Create dto) {

        User user = userDao.findById(dto.getUserId()).
                orElseThrow(NotFoundException::new);

        List<Post> postList = postDao.findByUser(user);

        if(user.getIsBlack().equals("y")) {
            throw new NotFoundException();
        }

        user.blackUser();

        for(Post p : postList) {
            p.delete();

            List<Comment> commentList = commentDao.findAllByPostAndUserAndIsDeleted(p, user,"y");

            for(Comment c : commentList) c.delete();
        }
        return blackListDao.save(dto.toEntity(user, dto.getReason())).getId();
    }

    @Transactional
    public List<BlackListDTO.Get> getBlackList() {
        return blackListDao.findAll().stream().map(BlackListDTO.Get::new).collect(Collectors.toList());
    }

    @Transactional
    public Long updateBlack(BlackListDTO.Update dto) {
        BlackList blackList = blackListDao.findById(dto.getId()).
                orElseThrow(NotFoundException::new);

        blackList.update(dto.getReason());

        return blackList.getId();
    }

    public void deleteBlack(Long id) {
        BlackList blackList = blackListDao.findById(id).
                orElseThrow(NotFoundException::new);

        blackList.getUser().nonBlackUser();

        blackListDao.delete(blackList);
    }

}