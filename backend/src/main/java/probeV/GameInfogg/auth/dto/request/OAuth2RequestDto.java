package probeV.GameInfogg.auth.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import probeV.GameInfogg.domain.user.User;
import probeV.GameInfogg.domain.user.constant.RoleType;

import java.util.Map;

@Builder
@Getter @Setter
@SuppressWarnings("unchecked")
public class OAuth2RequestDto {
    private String name;
    private String email;
    private String provider;
    private String nameAttributeKey;
    private String attributeCode;

    @Builder
    public OAuth2RequestDto(String name, String email, String provider, String nameAttributeKey, String attributeCode) {
        this.name = name;
        this.email = email;
        this.provider = provider;
        this.nameAttributeKey = nameAttributeKey;
        this.attributeCode = attributeCode;
    }

    // 카카오, 네이버 로그인 처리
    public static OAuth2RequestDto of(Map<String, Object> attributes, String userNameAttributeName, String registrationId) {
        if ("naver".equals(registrationId)) {
            return ofNaver("response", attributes);
        }
        return ofKakao("id", attributes);
    }

    private static OAuth2RequestDto ofNaver(String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuth2RequestDto.builder()
                .provider("naver")
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .nameAttributeKey(userNameAttributeName)
                .attributeCode((String) response.get("id"))
                .build();
    }

    private static OAuth2RequestDto ofKakao(String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String, Object>) kakaoAccount.get("profile");

        return OAuth2RequestDto.builder()
                .provider("kakao")
                .name(kakaoProfile.get("nickname").toString())
                .email(kakaoAccount.get("email").toString())
                .nameAttributeKey(userNameAttributeName)
                .attributeCode(attributes.get("id").toString())
                .build();
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .provider(provider)
                .attributeCode(attributeCode)
                .roleType(RoleType.USER)
                .build();
    }
}