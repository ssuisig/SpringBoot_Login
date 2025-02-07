package ssui.jwt_oauth2login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssui.jwt_oauth2login.entity.UserEntity2;

public interface UserRepository extends JpaRepository<UserEntity2, Long> {
    UserEntity2 findByUsername(String username);
}
