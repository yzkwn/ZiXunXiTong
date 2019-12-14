package com.hhlt.konsultado.util;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {

	public static LoginUser getLoginUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			return (LoginUser) authentication.getPrincipal();
		}

		return null;
	}

}
