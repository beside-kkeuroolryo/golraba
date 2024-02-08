package donggi.dev.kkeuroolryo.url.presentation.dto;

import donggi.dev.kkeuroolryo.url.domain.Url;

public record ShortUrlRequestDto(
    String originalData
) {
    public Url convertToEntity() {
        return new Url(originalData);
    }
}
