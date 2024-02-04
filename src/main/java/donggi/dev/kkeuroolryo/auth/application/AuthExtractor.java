package donggi.dev.kkeuroolryo.auth.application;

import donggi.dev.kkeuroolryo.auth.application.dto.AuthPayload;

public interface AuthExtractor {

    AuthPayload extract(String token);
}
