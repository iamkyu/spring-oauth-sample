package io.iamkyu.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.social.connect.UserProfile;

/**
 * @author Kj Nam
 */
@Getter
@Setter
@ToString
@Builder
public class UserCreateRequest {

    private String email;

    private String name;

    private String password;

    protected UserCreateRequest() {
    }

    public UserCreateRequest(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public static UserCreateRequest create(UserProfile userProfile) {
        return UserCreateRequest.builder()
                .email(userProfile.getEmail())
                .name(String.format("%s%s", userProfile.getLastName(), userProfile.getFirstName()))
                .build();
    }
}
