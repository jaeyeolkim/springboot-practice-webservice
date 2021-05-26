package org.egovframe.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaAuditing JPA Auditing 활성화 삭제하고 JpaConfig 클래스 생성하여 분리함 - WebMvcTest 스캔 대상에서 제외하기 위해
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
