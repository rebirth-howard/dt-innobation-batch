package com.deutschmotors.moduleapi.config.security;

import com.deutschmotors.moduleapi.domain.auth.model.AuthAuthenticationToken;
import com.deutschmotors.moduleapi.domain.auth.model.AuthUserDetails;
import com.deutschmotors.moduleapi.domain.auth.service.AuthService;
import com.deutschmotors.modulecommon.error.AuthErrorCode;
import com.deutschmotors.modulecommon.exception.CommonException;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!(authentication instanceof AuthAuthenticationToken requestAuth)) {
            throw new CommonException(AuthErrorCode.AUTH_EXCEPTION, "Only CustomAuthenticationToken is supported");
        }

        String username = requestAuth.getName();
        String password = requestAuth.getCredentials();

        AuthUserDetails authUserDetails = authService.loadUserByUsername(username);

        if (!passwordEncoder.matches(password, authUserDetails.getPassword())) {
            throw new CommonException(AuthErrorCode.ILLEGAL_ARGUMENT, "Invalid username or password");
        }

        return AuthAuthenticationToken.builder()
                .principal(authUserDetails)
                .credentials(null)
                .authorities(authUserDetails.getRoles())
                .authenticated(true)
                .build();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return AuthAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
