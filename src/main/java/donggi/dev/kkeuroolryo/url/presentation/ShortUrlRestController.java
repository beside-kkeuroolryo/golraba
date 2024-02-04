package donggi.dev.kkeuroolryo.url.presentation;

import donggi.dev.kkeuroolryo.common.response.ApiResponse;
import donggi.dev.kkeuroolryo.url.application.ShortUrlService;
import donggi.dev.kkeuroolryo.url.application.dto.ShortUrlDto;
import donggi.dev.kkeuroolryo.url.application.dto.ShortUrlResponseDto;
import donggi.dev.kkeuroolryo.url.presentation.dto.ShortUrlRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/golrabas/shortUrl")
public class ShortUrlRestController {

    private final ShortUrlService shortUrlService;

    @PostMapping
    public ApiResponse<ShortUrlResponseDto> encodeShortUrl(@RequestBody ShortUrlRequestDto request) {
        ShortUrlResponseDto shortUrlResponseDto = shortUrlService.encodeShortUrl(request);
        return ApiResponse.success(shortUrlResponseDto);
    }

    @GetMapping("/{shortUrl}")
    public ApiResponse<ShortUrlDto> decodeShortUrl(@PathVariable("shortUrl") String shortUrl) {
        ShortUrlDto shortUrlDto = shortUrlService.decodeShortUrl(shortUrl);
        return ApiResponse.success(shortUrlDto);
    }
}
