//package com.ssh.greenthumb.controller.login;
//
//import com.ssh.greenthumb.dao.user.UserRepository;
//import com.ssh.greenthumb.domain.user.User;
//import com.ssh.greenthumb.security.CurrentUser;
//import com.ssh.greenthumb.security.UserPrincipal;
//import lombok.RequiredArgsConstructor;
//import org.elasticsearch.ResourceNotFoundException;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RequiredArgsConstructor
//@RestController
//public class OAuthUserController {
//
//    private final UserRepository userDao;
//
//    @GetMapping("/user/me")
//    @PreAuthorize("hasRole('USER')")
//    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
//        return userDao.findById(userPrincipal.getId())
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
//    }
//
//}
