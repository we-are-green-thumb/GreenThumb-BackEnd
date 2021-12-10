package kr.pe.greenthumb.controller.login;

import kr.pe.greenthumb.dao.login.OAuthUserRepository;
import kr.pe.greenthumb.domain.login.OAuthUser;
import kr.pe.greenthumb.security.CurrentUser;
import kr.pe.greenthumb.security.UserPrincipal;
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
