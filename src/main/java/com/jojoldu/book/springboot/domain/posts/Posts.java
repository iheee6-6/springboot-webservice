package com.jojoldu.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //엔티티 클래스에는 setter 만들지 않음!!
@NoArgsConstructor // 위 두개는 롬복의 어노테이션. 기본생성자 자동추가 ex) public Posts(){} 깉은 효과
@Entity //jpa의 어노테이션. 테이블과 링크될 클래스임을 나타냄. 기본값으로 클래스의 카멜케이스 이름을 _으로 테이블 이름을 매칭함. ex) SalesManager.java -> sales_manager table
public class Posts { //실제 디비의 테이블과 매칭될 클래스 "Entity 클래스"라 칭함
    @Id //해당 테이블의 pk 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk 생성 규칙을 나타냄. GenerationType.IDENTITY 는 auto increment를 뜻함
    private Long id;

    @Column(length=500,nullable = false) //author 처럼 생략해도 되지만 기본값이외의 설정이 필요하면 사용함! 문자열읠 경우 varvhar255가 기본인데 500으로 늘리거나 타입을 바꾸거나
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    private String author;

    @Builder //해당 클래스의 빌더 패턴 클래스 생성. 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content,String author){
        this.title=title;
        this.content=content;
        this.author=author;
    }
}
