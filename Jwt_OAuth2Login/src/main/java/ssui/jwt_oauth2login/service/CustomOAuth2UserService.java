package ssui.jwt_oauth2login.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import ssui.jwt_oauth2login.dto.*;
import ssui.jwt_oauth2login.entity.UserEntity2;
import ssui.jwt_oauth2login.repository.UserRepository;

@Service
public class CustomOAuth2UserService  extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println(oAuth2User+"!!");

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2Response oAuth2Response = null;
        if(registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } else if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else{
            return null;
        }
        //리소스 서버에서 발급 받은 정보로 사용자 아이디를 만듦 ex)naver1234
        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        UserEntity2 existData = userRepository.findByUsername(username);

        if (existData == null) {

            UserEntity2 userEntity2 = new UserEntity2();
            userEntity2.setUsername(username);
            userEntity2.setEmail(oAuth2Response.getEmail());
            userEntity2.setName(oAuth2Response.getName());
            userEntity2.setRole("ROLE_USER");

            userRepository.save(userEntity2);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(username);
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole("ROLE_USER");

            return new CustomOAuth2User(userDTO);
        }
        else {

            existData.setEmail(oAuth2Response.getEmail());
            existData.setName(oAuth2Response.getName());

            userRepository.save(existData);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(existData.getUsername());
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole(existData.getRole());

            return new CustomOAuth2User(userDTO);
        }

    }
}
