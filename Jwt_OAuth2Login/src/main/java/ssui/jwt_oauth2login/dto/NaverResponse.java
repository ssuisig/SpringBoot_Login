package ssui.jwt_oauth2login.dto;

import java.util.Map;

public class NaverResponse implements OAuth2Response {

    //final -> 값을 한번만 할당
    //캐스팅 이유 get()은 object 반환
    private final Map<String, Object> attribute;
    public NaverResponse(Map<String, Object> attribute) {
        this.attribute = (Map<String, Object>) attribute.get("response");
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getProviderId() {
        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {
        return attribute.get("email").toString();
    }

    @Override
    public String getName() {
        return attribute.get("name").toString();
    }
}
