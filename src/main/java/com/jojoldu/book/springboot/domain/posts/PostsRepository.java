package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    //db layer 접근자.
    //entity 클래스랑 같은 위치에 있어야 함!!-> 도메인 패키지에서 함께 관리해야함

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") //springdatajpa에서 제공하지 않는 메소드는 이렇게 쿼리로 작성!
    List<Posts> findAllDesc();
}
