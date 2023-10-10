package donggi.dev.kkeuroolryo.web.url.dto;

import donggi.dev.kkeuroolryo.core.url.domain.Url;

public record ShortUrlRequestDto(
    String originalData
) {
    public Url convertToEntity() {
        return new Url(originalData);
    }
}
