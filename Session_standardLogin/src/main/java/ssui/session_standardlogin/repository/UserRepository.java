package ssui.session_standardlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssui.session_standardlogin.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByUsername(String username);

    UserEntity findByUsername(String username);
}
