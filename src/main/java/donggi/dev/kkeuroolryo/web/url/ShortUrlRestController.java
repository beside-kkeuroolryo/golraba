package donggi.dev.kkeuroolryo.web.url;

import donggi.dev.kkeuroolryo.common.response.ApiResponse;
import donggi.dev.kkeuroolryo.core.url.application.ShortUrlService;
import donggi.dev.kkeuroolryo.core.url.dto.ShortUrlResponseDto;
import donggi.dev.kkeuroolryo.web.url.dto.ShortUrlRequestDto;
import lombok.RequiredArgsConstructor;
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
    public ApiResponse<ShortUrlResponseDto> shortUrl(@RequestBody ShortUrlRequestDto request) {
        ShortUrlResponseDto shortUrlResponseDto = shortUrlService.shortenUrl(request);
        return ApiResponse.success(shortUrlResponseDto);
    }
}
