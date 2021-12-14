package com.ssh.greenthumb.controller.login;

import com.ssh.greenthumb.dao.login.OAuthUserRepository;
import com.ssh.greenthumb.security.CurrentUser;
import com.ssh.greenthumb.security.UserPrincipal;
import com.ssh.greenthumb.domain.login.OAuthUser;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.ResourceNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OAuthUserController {

    private final OAuthUserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public OAuthUser getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

}
