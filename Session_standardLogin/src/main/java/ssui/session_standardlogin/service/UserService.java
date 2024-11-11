package ssui.session_standardlogin.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ssui.session_standardlogin.dto.UserDTO;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;


@Service
public class UserService {

    public UserDTO getAuthenticatedUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //사용자 id
        String id = authentication.getName();

        //사용자 첫번째 role
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        //모든 role
//    String roles = authorities.stream()
//            .map(GrantedAuthority::getAuthority)
//            .collect(Collectors.joining(", "));
        return new UserDTO(id, role);
    }





}
