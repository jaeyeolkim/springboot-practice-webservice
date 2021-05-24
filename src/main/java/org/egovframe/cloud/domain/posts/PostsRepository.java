package org.egovframe.cloud.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Entity클래스, PK타입> 를 상속하면 기본적인 CRUD 메소드가 자동 생성된다.
// @Repository 를 추가할 필요도 없음
// Entity 클래스와 기본 Repository는 밀접한 관계이므로 도메인 패키지에서 함께 관리한다.
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
