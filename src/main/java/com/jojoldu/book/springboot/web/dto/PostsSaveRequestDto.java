package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto { //엔티티 클래스는 절대로 request/response 클래스로 사용하면 안됨 . 디비와 맞닿는 핵심 클래스임!!!
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content,String author){
        this.title=title;
        this.content=content;
        this.author=author;

    }
    public Posts toEntity() {
        return Posts.builder().title(title)
                .content(content)
                .author(author)
                .build();
    }
}
