package donggi.dev.kkeuroolryo.url.application;

import donggi.dev.kkeuroolryo.url.infrastructure.ShortUrlJpaRepository;
import donggi.dev.kkeuroolryo.url.domain.Url;
import donggi.dev.kkeuroolryo.url.application.dto.ShortUrlDto;
import donggi.dev.kkeuroolryo.url.application.dto.ShortUrlResponseDto;
import donggi.dev.kkeuroolryo.url.presentation.dto.ShortUrlRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShortUrlService {

    private final Base62Util base62Util;
    private final ShortUrlJpaRepository shortUrlRepository;

    @Transactional
    public ShortUrlResponseDto encodeShortUrl(ShortUrlRequestDto shortUrlRequestDto) {
        Url url = shortUrlRepository.save(shortUrlRequestDto.convertToEntity());
        String shortUrl = base62Util.encoding(url.getId());
        return new ShortUrlResponseDto(shortUrl);
    }

    @Transactional(readOnly = true)
    public ShortUrlDto decodeShortUrl(String shortUrl) {
        Long decoding = base62Util.decoding(shortUrl);
        Url url = shortUrlRepository.getById(decoding);
        return new ShortUrlDto(url.getOriginalData());
    }
}
