package com.ssh.greenthumb.auth.service;

import com.ssh.greenthumb.api.common.exception.OAuth2AuthenticationProcessingException;
import com.ssh.greenthumb.api.dao.user.UserRepository;
import com.ssh.greenthumb.auth.domain.AuthProvider;
import com.ssh.greenthumb.auth.domain.Role;
import com.ssh.greenthumb.api.domain.user.User;
import com.ssh.greenthumb.auth.info.OAuth2UserInfo;
import com.ssh.greenthumb.auth.info.OAuth2UserInfoFactory;
import com.ssh.greenthumb.auth.domain.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userDao;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    // 사용자 정보 추출
    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());

        if (!StringUtils.hasText(oAuth2UserInfo.getId())) {
            throw new OAuth2AuthenticationProcessingException("ProviderId not found from OAuth2 provider");
        }

        User userOptional = userDao.findByEmailAndIsDeleted(oAuth2UserInfo.getEmail(), "n");
        User user;

        if (userOptional != null) {
            user = userOptional;

            if(!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        user.getProvider() + " account. Please use your " + user.getProvider() +
                        " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        return userDao.save(User.builder()
                .provider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))
                .providerId(oAuth2UserInfo.getId())
                .nickName(oAuth2UserInfo.getName())
                .email(oAuth2UserInfo.getEmail())
                .imageUrl(oAuth2UserInfo.getImageUrl())
                .role(Role.USER)
                .build());
    }
    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        return existingUser.update(existingUser.getNickName(), existingUser.getImageUrl());
    }

}