package ssui.jwt_oauth2login.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CustomOAuth2User implements OAuth2User {
    private final UserDTO userDTO;
    public CustomOAuth2User(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    // 받은 데이터 값 return -> naver와 google의 형태가 달라서 안씀
    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    //role 값 return
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            //GrantedAuthority는 인터페이스로 재정의 필수
            @Override
            public String getAuthority() {
                return userDTO.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getName() {
        return userDTO.getName();
    }

    public String getUsername() {
        return userDTO.getUsername();
    }
}
