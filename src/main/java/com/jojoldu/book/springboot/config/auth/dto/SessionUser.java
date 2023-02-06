package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.User;
import lombok.Getter;

import javax.mail.Session;
import java.io.Serializable;

@Getter
public class SessionUser implements Serializable { //직렬화 구현
    //인증된 사용자 정보만 필요하니까 필드는 세개
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
