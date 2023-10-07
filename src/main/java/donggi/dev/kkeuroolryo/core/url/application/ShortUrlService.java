package donggi.dev.kkeuroolryo.core.url.application;

import donggi.dev.kkeuroolryo.core.url.domain.ShortUrlJpaRepository;
import donggi.dev.kkeuroolryo.core.url.domain.Url;
import donggi.dev.kkeuroolryo.core.url.dto.ShortUrlResponseDto;
import donggi.dev.kkeuroolryo.web.url.dto.ShortUrlRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShortUrlService {

    private final Base62Util base62Util;
    private final ShortUrlJpaRepository shortUrlRepository;

    @Transactional
    public ShortUrlResponseDto shortenUrl(ShortUrlRequestDto shortUrlRequestDto) {
        Url url = shortUrlRepository.save(shortUrlRequestDto.convertToEntity());
        String shortUrl = base62Util.encoding(url.getId());
        return new ShortUrlResponseDto(shortUrl);
    }
}
