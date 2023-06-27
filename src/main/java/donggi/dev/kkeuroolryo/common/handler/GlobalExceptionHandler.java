package donggi.dev.kkeuroolryo.common.handler;

import donggi.dev.kkeuroolryo.common.exception.GolrabaException;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(GolrabaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleClientException(GolrabaException exception) {
        log.debug("[golraba-exception] code = {}, message = {}, cause = {}",
            exception.getCode(), exception.getMessage(), exception.getCause());
        HashMap<Object, Object> errorMessageMap = new HashMap<>();
        errorMessageMap.put("code", exception.getCode());
        errorMessageMap.put("message", exception.getMessage());
        errorMessageMap.put("cause", exception.getCause());
        return ResponseEntity.badRequest().body(errorMessageMap);
    }

}
