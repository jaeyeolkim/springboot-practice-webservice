package org.egovframe.cloud.config.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "userAuditAware") // JPA Auditing 활성화
public class JpaConfig {
}
