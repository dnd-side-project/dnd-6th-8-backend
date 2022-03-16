package com.travel.domain.config.auth;


import com.travel.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserDto {
    private String email;
    private String name;
    private String picture;

    @Builder
    public UserDto(String email, String name, String picture) {
        this.email = email;
        this.name = name;
        this.picture = picture;
    }

    public User toEntity() {
        return User.builder().email(email).profilePicture(picture)
                .userName(name).build();
    }

    public UserDto(User entity) {
        this.email = entity.getEmail();
        this.picture = entity.getProfilePicture();
        this.name = entity.getUserName();
    }
}


