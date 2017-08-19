package io.iamkyu.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Kj Nam
 */
@Entity
@Getter
@Setter
@ToString
public class User implements Serializable {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String email;

    private String password;

    protected User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static User create(UserCreateRequest createRequest) {
        User user = new User();
        user.setName(createRequest.getName());
        user.setEmail(createRequest.getEmail());
        user.setEmail(createRequest.getPassword());
        return user;
    }

    public void setEncodePassword(String encodePassword) {
        this.password = encodePassword;
    }
}
