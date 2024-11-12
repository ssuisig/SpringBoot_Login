package ssui.session_standardlogin.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ssui.session_standardlogin.entity.UserEntity;
import ssui.session_standardlogin.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(of= {"username"}) //같은 사용자가 다른 객체로 인식되지 않도록 함, 중복 로그인 방지
public class CustomUserDetails implements UserDetails {

    private UserEntity userEntity;
    private String username;

    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
        this.username = userEntity.getUsername();
    }

    //사용자의 역할을 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return userEntity.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return username;
    }

    //만료됐는지, 이용가능한지 등등.. 임의로 true
    @Override
    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
        return true;
    }

    @Override
    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
        return true;
    }
}
