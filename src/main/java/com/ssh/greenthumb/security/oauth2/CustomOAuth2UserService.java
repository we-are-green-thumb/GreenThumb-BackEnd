package com.ssh.greenthumb.security.oauth2;

import com.ssh.greenthumb.common.exception.OAuth2AuthenticationProcessingException;
import com.ssh.greenthumb.dao.user.UserRepository;
import com.ssh.greenthumb.domain.login.AuthProvider;
import com.ssh.greenthumb.domain.user.User;
import com.ssh.greenthumb.security.UserPrincipal;
import com.ssh.greenthumb.security.oauth2.user.OAuth2UserInfo;
import com.ssh.greenthumb.security.oauth2.user.OAuth2UserInfoFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    // 사용자 정보 추출
    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        AuthProvider authProvider = AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId());
//        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
//            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
//        }

        OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(authProvider, oAuth2User.getAttributes());
        User savedUser = userRepository.findByProviderId(userInfo.getId());
        if(savedUser != null) {
            if(!savedUser.getProvider().equals(authProvider)) {
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        savedUser.getProvider() + " account. Please use your " + savedUser.getProvider() +
                        " account to login.");
            }
            updateExistingUser(savedUser, userInfo);
        } else {
            registerNewUser(userInfo, authProvider);
        }

        return UserPrincipal.create(savedUser, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserInfo oAuth2UserInfo, AuthProvider authProvider) {
        return userRepository.save(User.builder()
                .provider(authProvider)
                .providerId(oAuth2UserInfo.getId())
                .nickName(oAuth2UserInfo.getName())
                .email(oAuth2UserInfo.getEmail())
                .imageUrl(oAuth2UserInfo.getImageUrl())
                .build());
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        return userRepository.save(existingUser.builder()
                .nickName(oAuth2UserInfo.getName())
                .imageUrl(oAuth2UserInfo.getImageUrl())
                .build());
    }

}