package org.egovframe.cloud.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.egovframe.cloud.domain.BaseTimeEntity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter // @Setter 는 만들지 않는다. 값의 변경은 목적과 의도를 나타낼 수 있는 public 메소드를 만들어 처리한다.
@NoArgsConstructor // 기본 생성자 추가
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
