package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    //db layer 접근자.
    //entity 클래스랑 같은 위치에 있어야 함!!-> 도메인 패키지에서 함께 관리해야함

}
