package ssui.session_standardlogin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class UserEntity {
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 자동 증가
    private int id;

    @Column(unique = true)
    private String username;
    private String password;

    private String role;
}
