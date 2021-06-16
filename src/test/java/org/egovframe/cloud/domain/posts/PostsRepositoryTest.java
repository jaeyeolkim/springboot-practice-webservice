package org.egovframe.cloud.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        /**
         이후 테스트 코드에 영향을 끼치지 않기 위해
         테스트 메소드가 끝날때마다 repository 전체 비우는 코드
         **/
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        // given
        // save는 insert or update 처리된다
        postsRepository.save(Posts.builder()
                .title("테스트 게시글")
                .content("테스트 본문")
                .author("kjy")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo("테스트 게시글");
        assertThat(posts.getContent()).isEqualTo("테스트 본문");
    }

    @Test
    public void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.of(2021, 5, 24, 14, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .author("author")
                .content("content")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println("createDate=" + posts.getCreatedDate() + ", modifiedDate=" + posts.getModifiedDate());
        System.out.println("createBy=" + posts.getCreatedBy() + ", modifiedBy=" + posts.getLastModifiedBy());
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }

    @Test
    public void 게시글_페이징() {
        // given
        // save는 insert or update 처리된다
        postsRepository.save(Posts.builder()
                .title("테스트1 게시글1")
                .content("테스트 본문")
                .author("kjy")
                .build());
        postsRepository.save(Posts.builder()
                .title("테스트1 게시글2")
                .content("테스트 본문")
                .author("kjy")
                .build());
        postsRepository.save(Posts.builder()
                .title("테스트1 게시글3")
                .content("테스트 본문")
                .author("kjy")
                .build());
        postsRepository.save(Posts.builder()
                .title("테스트2 게시글1")
                .content("테스트 본문")
                .author("kjy")
                .build());

        // when
        PageRequest pageRequest = PageRequest.of(1, 2, Sort.by(Sort.Direction.DESC, "createdDate"));
        Page<Posts> page = postsRepository.findByTitleContainingIgnoreCase("테스트1", pageRequest);

        // then
        assertThat(page.getContent().size()).isEqualTo(1); // 현재 페이지의 건수
        assertThat(page.getTotalElements()).isEqualTo(3); // 전체 건수, 반환 타입이 Page이면 전체 건수가 추가 조회됨
        assertThat(page.getNumber()).isEqualTo(1); // 페이지 번호
        assertThat(page.getTotalPages()).isEqualTo(2); // 전체 페이지 수
        assertThat(page.isFirst()).isFalse(); // 첫 번째 페이지 여부
        assertThat(page.hasNext()).isFalse(); // 다음 페이지 존재 여부

    }
}