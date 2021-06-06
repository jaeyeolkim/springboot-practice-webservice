package org.egovframe.cloud.service;

import lombok.RequiredArgsConstructor;
import org.egovframe.cloud.domain.posts.Posts;
import org.egovframe.cloud.domain.posts.PostsRepository;
import org.egovframe.cloud.api.dto.PostsListResponseDto;
import org.egovframe.cloud.api.dto.PostsResponseDto;
import org.egovframe.cloud.api.dto.PostsSaveRequestDto;
import org.egovframe.cloud.api.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        // 존재하는 Posts인지 확인 후 처리
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선된다.
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // Posts의 Stream을 map을 통해 PostsListResponseDto로 변환한다. 실제로 .map(posts -> new PostsListResponseDto(posts)) 과 같다.
                .collect(Collectors.toList());
    }

}
