package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    @GetMapping("/")
    public String index(Model model){ //Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장. findAllDesc()의 결과를 posts로 index.mustache에 전달하는 역할함
        model.addAttribute("posts", postsService.findAllDesc());
        return "index"; //view resolver가 resources/templates/index.mustache로 처리할거임
    }
    
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save"; //posts-save mustache 호출
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id,Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }

}
