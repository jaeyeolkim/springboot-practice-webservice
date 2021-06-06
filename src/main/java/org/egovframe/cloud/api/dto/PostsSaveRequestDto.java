package org.egovframe.cloud.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.egovframe.cloud.domain.posts.Posts;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // PostsSaveRequestDto 의 필드 값을 Posts Entity 빌더를 사용하여 주입 후 Posts를 리턴한다.
    // PostsSaveRequestDto 가 가지고 있는 Posts 의 필드만 세팅할 수 있게 된다.
    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
