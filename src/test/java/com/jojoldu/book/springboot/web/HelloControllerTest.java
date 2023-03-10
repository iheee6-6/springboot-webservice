package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // 테스트를 진행할때 junit에 내장된 실행자 외에 다른 실행자를 실행시킴.
//// SpringRunner라는 스프링 실행자를 실행시킴.
// 즉, 스프링 부트 테스트와 junit 사이에 연결자 역할임!!
@WebMvcTest(controllers=HelloController.class,
            excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
        classes = SecurityConfig.class)
            }) //mvc 에 집중할 수 있는 어노테이션. @Controller , @ControllerAdive 사용 가능
public class HelloControllerTest {

    @Autowired //스프링이 관리하는 빈 주입 받기
    private MockMvc mvc; //웹api를 테스트할때 사용. mvc테스트의 시작점! . http get,post에 대한 api 테스트 가능하게 함!


    @WithMockUser(roles = "USER")  //@WithMockUser 어노테이션은 테스트에 필요한 인증된 인증 정보를 제공하며 간단한 정보를 기본으로 설정할 수 있게끔 도와준다
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) //MockMvc를 통해 /hello 주소로 http get 요청을 함. 체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언할 수 있음.
                .andExpect(status().isOk()) //mvc.perform의 결과를 검증하는 것. http header 의 status를 검증함! (200,404,500 등 인지 점검/ ok로 200인지 확인)
                .andExpect(content().string(hello)); //mvc.perform의 결과를 검증하는 것. 응답 본문의 내용을 검증! Controoler에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증!
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name ="hello";
        int amount=1000;
        mvc.perform(get("/hello/dto")
                .param("name",name) //api 테스트할때 사용될 요청 파라미터를 설정! 단, 값은 String만 됨
                .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount",is(amount)));
                // jsonPath : json 응답값을 필드별로 검증할 수 있는 메소드.
                // $를 기준으로 필드명을 명시함.
    }


}
