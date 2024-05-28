package com.acme.eazyschool.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

// Implement this interface to provide the current auditor i.e. the current user
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    // Get details of user currently logged in trying to perform certain actions
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
