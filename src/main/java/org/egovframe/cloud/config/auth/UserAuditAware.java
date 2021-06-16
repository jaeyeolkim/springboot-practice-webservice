package org.egovframe.cloud.config.auth;

import org.egovframe.cloud.domain.user.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAuditAware implements AuditorAware<User> {
    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.empty();
    }
}