package org.egovframe.cloud.web;

import org.egovframe.cloud.config.auth.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * ExtendWith(SpringExtension.class) : 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 한다.
 * WebMvcTest :  @Controller 사용 가능, Service, JPA 등은 사용 불가.
 * 스프링 MVC 에 집중할 수 있는 어노테이션, @Controller, @ControllerAdvice 등을 사용 가능하게 한다.
 * SecurityConfig는 읽었지만, SecurityConfig를 생성하기 위해 필요한 CustomOAuth2UserService는 읽을수가 없어 오류가 발생하다.
 * 따라서, 스캔 대상에서 SecurityConfig를 제거한다.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        })
class HelloControllerTest {

    @Autowired
    private MockMvc mvc; // 웹 API를 테스트할 때 사용

    @Test
    @WithMockUser(roles = "USER")
    public void hello가_리턴된다() throws Exception {
        // given
        String hello = "hello";

        // when
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));

    }

}