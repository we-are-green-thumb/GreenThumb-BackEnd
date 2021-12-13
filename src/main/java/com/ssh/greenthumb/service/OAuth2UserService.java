package com.ssh.greenthumb.service;

import com.ssh.greenthumb.common.domain.BaseTimeEntity;
import com.ssh.greenthumb.dao.login.OAuthUserRepository;
import com.ssh.greenthumb.domain.login.OAuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OAuth2UserService extends BaseTimeEntity {

    private final OAuthUserRepository userRepository;

    public OAuthUser getUser(String userName) {
        return userRepository.findByName(userName);
    }

}
