package org.egovframe.cloud.config.auth.dto;

import org.egovframe.cloud.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

// session 에 저장하기 위해 직렬화를 구현하였다
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    // 인증된 사용자 정보만 필요하여 세 컬럼만 정의
    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
