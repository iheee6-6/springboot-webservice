package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //jpa auditing 활성화
@SpringBootApplication // 스프링 부트의 자동 설정, 스프링 bean 읽기와 생성을 모두 자동으로 설정함, 이것이 있는 위치부터 설정을 읽어가기때문에
// 이 클래스는 가장 최상단에 있어야함.
public class Application { //main 클래스
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);// 내장 was
    }
}
