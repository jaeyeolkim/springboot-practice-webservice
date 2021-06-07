package org.egovframe.cloud.domain.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// JpaRepository<Entity클래스, PK타입> 를 상속하면 기본적인 CRUD 메소드가 자동 생성된다.
// @Repository 를 추가할 필요도 없음
// Entity 클래스와 기본 Repository는 밀접한 관계이므로 도메인 패키지에서 함께 관리한다.
public interface PostsRepository extends JpaRepository<Posts, Long> {

    // Spring Data Jpa 에서 제공하지 않는 메소드는 이렇게 쿼리로 작성할 수 있다
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

    // paging
    Page<Posts> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
