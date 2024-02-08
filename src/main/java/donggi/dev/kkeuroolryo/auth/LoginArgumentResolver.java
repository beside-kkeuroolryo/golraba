package donggi.dev.kkeuroolryo.auth;

import donggi.dev.kkeuroolryo.auth.application.AuthExtractor;
import donggi.dev.kkeuroolryo.auth.application.dto.AuthPayload;
import donggi.dev.kkeuroolryo.common.exception.BadRequestException;
import donggi.dev.kkeuroolryo.common.exception.ErrorCodeAndMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class LoginArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String BEARER_TYPE = "Bearer ";

    private final AuthExtractor authExtractor;

    public LoginArgumentResolver(final AuthExtractor authExtractor) {
        this.authExtractor = authExtractor;
    }

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.getParameterType().equals(Long.class) &&
            parameter.hasParameterAnnotation(Auth.class);
    }

    @Override
    public Object resolveArgument(
        final MethodParameter parameter,
        final ModelAndViewContainer mavContainer,
        final NativeWebRequest webRequest,
        final WebDataBinderFactory binderFactory
    ) {
        final HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        if (request == null) {
            throw new BadRequestException(ErrorCodeAndMessage.INVALID_REQUEST);
        }

        final String accessToken = extractAccessToken(request.getHeader(HttpHeaders.AUTHORIZATION));
        final AuthPayload authPayload = authExtractor.extract(accessToken);
        return authPayload.id();
    }

    private String extractAccessToken(final String header) {
        if (header != null && header.startsWith(BEARER_TYPE)) {
            return header.substring(BEARER_TYPE.length()).trim();
        }

        throw new BadRequestException(ErrorCodeAndMessage.INVALID_AUTH_TOKEN);
    }
}
