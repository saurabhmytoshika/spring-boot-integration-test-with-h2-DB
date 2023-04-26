package com.gymmgtsystem.listener;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditEventListerner implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//		if (authentication == null || !authentication.isAuthenticated() ||
//				authentication instanceof AnonymousAuthenticationToken) {
//			return Optional.empty();
//		}
//
//		UserInfo userInfo = (UserInfo) authentication.getPrincipal();
		

		return Optional.ofNullable("SYSTEM");
	}

}


