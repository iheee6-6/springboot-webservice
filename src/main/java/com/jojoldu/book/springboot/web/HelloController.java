package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // json을 반환하는 컨트롤러 @ResponseBody랑 같은 역할
public class HelloController {

    @GetMapping("/hello") //http method인 get의 요청을 받을 수 있는 API @RequestMapping(method=RequestMethod.Get) 역할
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        return new HelloResponseDto(name,amount);
    } //@RequestParam이란 외부에서 api로 넘긴 파라미터를 가져오는 어노테이션
        // 여기서는 외부에서 name이란 이름으로 넘긴 파라미터를 메소드 파라미터 name에 저장하게 됨.
}
