package kr.pe.greenthumb.security;


import kr.pe.greenthumb.common.exception.ResourceNotFoundException;
import kr.pe.greenthumb.dao.login.OAuthUserRepository;
import kr.pe.greenthumb.domain.login.OAuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final OAuthUserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        OAuthUser user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(email + "로 된 사용자를 찾을 수 없습니다.")
                );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        OAuthUser user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }

}

