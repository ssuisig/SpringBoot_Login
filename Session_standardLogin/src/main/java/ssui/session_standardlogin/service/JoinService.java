package ssui.session_standardlogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ssui.session_standardlogin.dto.JoinDTO;
import ssui.session_standardlogin.entity.UserEntity;
import ssui.session_standardlogin.repository.UserRepository;

@Service
public class JoinService {
    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public JoinService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void joinProcess(JoinDTO joinDTO) {
        //db에 이미 동일한 username을 가진 회원이 존재하는지?
        boolean isUser = userRepository.existsByUsername(joinDTO.getUsername());
        if(isUser){
            return;
        }

        UserEntity data = new UserEntity();

        data.setUsername(joinDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));

        data.setRole("ROLE_USER");


        userRepository.save(data);
    }
}

