package org.egovframe.cloud.web;

import lombok.RequiredArgsConstructor;
import org.egovframe.cloud.service.PostsService;
import org.egovframe.cloud.web.dto.PostsListResponseDto;
import org.egovframe.cloud.web.dto.PostsResponseDto;
import org.egovframe.cloud.web.dto.PostsSaveRequestDto;
import org.egovframe.cloud.web.dto.PostsUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // final이 선언된 모든 필드를 인자값으로 하는 생성자를 대신 생성하여, 빈을 생성자로 주입받게 한다.
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts")
    public List<PostsListResponseDto> findAllDesc() {
        return postsService.findAllDesc();
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

}
