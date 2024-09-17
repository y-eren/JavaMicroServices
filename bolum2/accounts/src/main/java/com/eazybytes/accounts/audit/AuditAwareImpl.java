package com.eazybytes.accounts.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

// bu isim herhangi bir şey olabilir sadece sınıfla aynı ada sahip olsun diye yapıyoruz.
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("ACCOUNTS_MS");
	}

}
