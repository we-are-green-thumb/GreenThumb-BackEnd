//package com.ssh.greenthumb.security.oauth2;
//
//import com.ssh.greenthumb.common.exception.OAuth2AuthenticationProcessingException;
//import com.ssh.greenthumb.dao.user.UserRepository;
//import com.ssh.greenthumb.domain.login.AuthProvider;
//import com.ssh.greenthumb.domain.user.User;
//import com.ssh.greenthumb.security.UserPrincipal;
//import com.ssh.greenthumb.security.oauth2.user.OAuth2UserInfo;
//import com.ssh.greenthumb.security.oauth2.user.OAuth2UserInfoFactory;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.InternalAuthenticationServiceException;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
//import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Service;
//
//@RequiredArgsConstructor
//@Service
//public class CustomOAuth2UserService extends DefaultOAuth2UserService {
//
//    @Autowired
//    private UserRepository userDao;
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
//        OAuth2User user = super.loadUser(oAuth2UserRequest);
//
//        try {
//            return processOAuth2User(oAuth2UserRequest, user);
//        } catch (AuthenticationException ex) {
//            throw ex;
//        } catch (Exception ex) {
//            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
//            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
//        }
//    }
//
//    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, User user) {
//        AuthProvider authProvider = AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase());
//        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(authProvider, user.getAttributes());
//        User savedUser = userDao.findByEmail(oAuth2UserInfo.getEmail());
//        User user;
//
//        if(savedUser.isPresent()) {
//            user = savedUser.get();
//            if(!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
//                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
//                        user.getProvider() + " account. Please use your " + user.getProvider() +
//                        " account to login.");
//            }
//            user = updateExistingUser(user, oAuth2UserInfo);
//        } else {
//            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
//        }
//
//        return UserPrincipal.create(user, user.getAttributes());
//    }
//
//    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
//        return userDao.save(User.builder()
//                .provider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))
//                .providerId(oAuth2UserInfo.getId())
//                .nickName(oAuth2UserInfo.getName())
//                .email(oAuth2UserInfo.getEmail())
//                .imageUrl(oAuth2UserInfo.getImageUrl())
//                .build());
//
//    }
//
//    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
//        return userDao.save(User.builder()
//                .email(oAuth2UserInfo.getEmail())
//                .imageUrl(oAuth2UserInfo.getImageUrl())
//                .build());
//
//    }
//
//}