package ssui.jwt_oauth2login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ssui.jwt_oauth2login.jwt.JWTFilter;
import ssui.jwt_oauth2login.jwt.JWTUtil;
import ssui.jwt_oauth2login.oauth2.CustomSuccessHandler;
import ssui.jwt_oauth2login.service.CustomOAuth2UserService;


//Spring Security 환경 설정을 구성
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    //CustomOAuth2UserService 등록해야 사용가능
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomSuccessHandler customSuccessHandler;
    private final JWTUtil jwtUtil;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, CustomSuccessHandler customSuccessHandler, JWTUtil jwtUtil) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.customSuccessHandler = customSuccessHandler;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //csrf disable
        http
                .csrf((auth)-> auth.disable());

        //oauth 방식 로그인을 사용하기 때문에

        //From 로그인 방식 disable
        http
                .formLogin((auth)-> auth.disable());
        //HTTP Basic 인증 방식 disable
        http
                .httpBasic((auth)-> auth.disable());

        //JWTFilter 추가
        http
                .addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        //oauth2 커스텀
        //http.oauth2Login(oauth2 -> ...) → OAuth 2.0 로그인을 사용하겠다는 설정
        //.userInfoEndpoint() → 사용자 정보 가져오는 엔드포인트 설정
        //.userService(customOAuth2UserService) → 사용자 정보를 커스텀 서비스에서 처리
        http
                .oauth2Login((oauth2) -> oauth2
                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                                .userService(customOAuth2UserService))
                        .successHandler(customSuccessHandler)
                );

        //경로별 인가 작업
        http
                .authorizeHttpRequests((auth)->auth
                        .requestMatchers("/").permitAll() //루트는 모두
                        .anyRequest().authenticated());     //나머지는 로그인한 사용자만

        //jwt를 사용하기 때문에
        //세션 설정 : STATELESS
        http
                .sessionManagement((session)-> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

}
