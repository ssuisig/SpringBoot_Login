package ssui.jwt_oauth2login.dto;

public interface OAuth2Response {
    //제공자 naver, google..
    String getProvider();
    //제공자에서 발급해주는 아이디
    String getProviderId();
    //이메일
    String getEmail();
    //사용자 이름
    String getName();
}
