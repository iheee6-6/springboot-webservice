package com.jojoldu.book.springboot.domain.user;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User  extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column
    private String picture;
    @Enumerated(EnumType.STRING) //jpa로 디비로 저장할때 Enum값을 어떤 형태로 저장할지 결정하는 것! 기본적으로는 int형임.
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture,Role role){
        this.name=name;
        this.email=email;
        this.picture=picture;
        this.role=role;
    }

    public User update(String name,String picture){
        this.name=name;
        this.picture=picture;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
